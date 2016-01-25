package com.petsknow.doctor.sessionmodule.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.domain.EaseEmojicon;
import com.easemob.easeui.utils.EaseCommonUtils;
import com.easemob.easeui.widget.EaseChatExtendMenu;
import com.easemob.easeui.widget.EaseChatInputMenu;
import com.easemob.easeui.widget.EaseChatMessageList;
import com.easemob.easeui.widget.EaseTitleBar;
import com.easemob.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.easemob.util.PathUtil;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;

import java.io.File;
import java.util.List;

/**
 * Created by yukuo on 2016/1/25.
 * 这是一个聊天的页面
 */
public class ChatActivity extends BaseActivity {
    static final int ITEM_TAKE_PICTURE = 1;
    static final int ITEM_PICTURE = 2;
    private int[] itemStrings = {com.easemob.easeui.R.string.attach_take_pic, com.easemob.easeui.R.string.attach_picture};
    private EaseTitleBar title_bar;
    private EaseChatMessageList message_list;
    private EaseChatInputMenu input_menu;
    protected int[] itemdrawables = {com.easemob.easeui.R.drawable.ease_chat_takepic_selector, com.easemob.easeui.R.drawable.ease_chat_image_selector};
    protected int[] itemIds = {ITEM_TAKE_PICTURE, ITEM_PICTURE};
    private MyItemClickListener extendMenuItemClickListener;
    private File cameraFile;
    protected static final int REQUEST_CODE_CAMERA = 2;
    protected static final int REQUEST_CODE_LOCAL = 3;
    private ListView listView1;
    private String toChatUsername;
    private ListView listView;
    private EMConversation conversation;
    protected int pagesize = 20;
    protected InputMethodManager inputManager;
    private boolean isMessageListInited;
    @Override
    public void initView() {
        title_bar = (EaseTitleBar) findViewById(R.id.title_bar);
        message_list = (EaseChatMessageList) findViewById(R.id.message_list);
        input_menu = (EaseChatInputMenu) findViewById(R.id.input_menu);
    }

    @Override
    public void setListener() {
        title_bar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initdata() {
        title_bar.setBackgroundColor(getResources().getColor(R.color.themecolor));
        listView = message_list.getListView();
        extendMenuItemClickListener = new MyItemClickListener();
        registerExtendMenuItem();
        input_menu.init(null);
        input_menu.setChatInputMenuListener(new EaseChatInputMenu.ChatInputMenuListener() {
            @Override
            public void onSendMessage(String content) {
                // 发送文本消息
                sendTextMessage(content);
            }

            @Override
            public void onBigExpressionClicked(EaseEmojicon emojicon) {

            }

            @Override
            public boolean onPressToSpeakBtnTouch(View v, MotionEvent event) {
                return false;
            }
        });
        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        onConversationInit();
        onMessageListInit();
    }

    protected void onMessageListInit() {
        message_list.init(toChatUsername, 1, chatFragmentListener != null ?
                chatFragmentListener.onSetCustomChatRowProvider() : null);
        //设置list item里的控件的点击事件
       // setListItemClickListener();

        message_list.getListView().setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                input_menu.hideExtendMenuContainer();
                return false;
            }
        });
        isMessageListInited = true;
    }

    /**
     * 隐藏软键盘
     */
    protected void hideKeyboard() {
        if (this.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (this.getCurrentFocus() != null)
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected void onConversationInit() {
        // 获取当前conversation对象
        conversation = EMChatManager.getInstance().getConversation(toChatUsername);
        // 把此会话的未读数置为0
        conversation.markAllMessagesAsRead();
        // 初始化db时，每个conversation加载数目是getChatOptions().getNumberOfMessagesLoaded
        // 这个数目如果比用户期望进入会话界面时显示的个数不一样，就多加载一些
        final List<EMMessage> msgs = conversation.getAllMessages();
        int msgCount = msgs != null ? msgs.size() : 0;
        if (msgCount < conversation.getAllMsgCount() && msgCount < pagesize) {
            String msgId = null;
            if (msgs != null && msgs.size() > 0) {
                msgId = msgs.get(0).getMsgId();
            }
            conversation.loadMoreMsgFromDB(msgId, pagesize - msgCount);
        }

    }


    /**
     * 扩展菜单栏item点击事件
     */
    class MyItemClickListener implements EaseChatExtendMenu.EaseChatExtendMenuItemClickListener {

        @Override
        public void onClick(int itemId, View view) {
//            if(chatFragmentListener != null){
//                if(chatFragmentListener.onExtendMenuItemClick(itemId, view)){
//                    return;
//                }
//            }
            switch (itemId) {
                case ITEM_TAKE_PICTURE: // 拍照
                    selectPicFromCamera();
                    break;
                case ITEM_PICTURE:
                    selectPicFromLocal(); // 图库选择图片
                    break;
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        toChatUsername = getIntent().getStringExtra("toChatUsername");
        initView();
        setListener();
        initdata();
    }

    /**
     * 注册底部菜单扩展栏item; 覆盖此方法时如果不覆盖已有item，item的id需大于3
     */
    protected void registerExtendMenuItem() {
        for (int i = 0; i < itemStrings.length; i++) {
            input_menu.registerExtendMenuItem(itemStrings[i], itemdrawables[i], itemIds[i], extendMenuItemClickListener);
        }
    }

    /**
     * 照相获取图片
     */
    protected void selectPicFromCamera() {
        if (!EaseCommonUtils.isExitsSdcard()) {
            //  Toast.makeText(getApplicationContext(), com.easemob.easeui.R.string.sd_card_does_not_exist, 0).show();
            return;
        }

        cameraFile = new File(PathUtil.getInstance().getImagePath(), EMChatManager.getInstance().getCurrentUser()
                + System.currentTimeMillis() + ".jpg");
        cameraFile.getParentFile().mkdirs();
        startActivityForResult(
                new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile)),
                REQUEST_CODE_CAMERA);
    }

    /**
     * 从图库获取图片
     */
    protected void selectPicFromLocal() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");

        } else {
            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(intent, REQUEST_CODE_LOCAL);
    }

    /**
     * 发送消息
     */
    //文字消息
    protected void sendTextMessage(String content) {
        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
        sendMessage(message);
    }

    //图片消息
    protected void sendImageMessage(String imagePath) {
        EMMessage message = EMMessage.createImageSendMessage(imagePath, false, toChatUsername);
        sendMessage(message);
    }

    protected void sendMessage(EMMessage message) {
//        if(chatFragmentListener != null){
//            //设置扩展属性
//            chatFragmentListener.onSetMessageAttributes(message);
//        }
        //发送消息
        EMChatManager.getInstance().sendMessage(message, null);
        //刷新ui
        message_list.refreshSelectLast();
    }
    protected EaseChatFragmentListener chatFragmentListener;

    public void setChatFragmentListener(EaseChatFragmentListener chatFragmentListener){
        this.chatFragmentListener = chatFragmentListener;
    }

    public interface EaseChatFragmentListener{
        /**
         * 设置消息扩展属性
         */
        void onSetMessageAttributes(EMMessage message);

        /**
         * 进入会话详情
         */
        void onEnterToChatDetails();

        /**
         * 用户头像点击事件
         * @param username
         */
        void onAvatarClick(String username);

        /**
         * 消息气泡框点击事件
         */
        boolean onMessageBubbleClick(EMMessage message);

        /**
         * 消息气泡框长按事件
         */
        void onMessageBubbleLongClick(EMMessage message);

        /**
         * 扩展输入栏item点击事件,如果要覆盖EaseChatFragment已有的点击事件，return true
         * @param view
         * @param itemId
         * @return
         */
        boolean onExtendMenuItemClick(int itemId, View view);

        /**
         * 设置自定义chatrow提供者
         * @return
         */
        EaseCustomChatRowProvider onSetCustomChatRowProvider();
    }
}
