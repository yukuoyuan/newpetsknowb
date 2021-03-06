package com.petsknow.doctor.usermodule.bean;

import java.util.List;

/**
 * Created by yukuo on 2016/1/21.
 */
public class LoginBean {
    /**
     * data : [{"areaId":"","attitudeAverage":0,"avatarUrl":"iv_wenzhen_top.9.png","certificate1Number":"","certificate1PhotoUrl":"","certificate2Number":"","certificate2PhotoUrl":"","channel":"chongzhidao","cityId":1,"ctime":null,"disturbState":0,"easemobId":325,"easemobName":"doctor_57","evaluation":0,"hospital":"你猜猜","hospitalId":0,"icWithHandPhotoUrl":"android571447228172803","id":57,"identifyNumber":"","identityCard":"130425199002051489","level":0,"modiTime":1447228140000,"noCertifyHospital":"","password":"","personPhotoUrl":"android571447228173298","phone":"17090119970","qualification":0,"qualityAverage":0,"sign":"","signPhotoUrl":"","speedAverage":0,"status":1,"timesOfWork":0,"title":"院长","trueName":"于医生","updateAdmitResult":1,"username":"","vcode":"13fadc33790b499e8d27612e2f73b111"}]
     * msg : success
     * status : 0
     * time : 1453363729083
     */

    private String msg;
    private int status;
    private long time;
    /**
     * areaId :
     * attitudeAverage : 0
     * avatarUrl : iv_wenzhen_top.9.png
     * certificate1Number :
     * certificate1PhotoUrl :
     * certificate2Number :
     * certificate2PhotoUrl :
     * channel : chongzhidao
     * cityId : 1
     * ctime : null
     * disturbState : 0
     * easemobId : 325
     * easemobName : doctor_57
     * evaluation : 0
     * hospital : 你猜猜
     * hospitalId : 0
     * icWithHandPhotoUrl : android571447228172803
     * id : 57
     * identifyNumber :
     * identityCard : 130425199002051489
     * level : 0
     * modiTime : 1447228140000
     * noCertifyHospital :
     * password :
     * personPhotoUrl : android571447228173298
     * phone : 17090119970
     * qualification : 0
     * qualityAverage : 0
     * sign :
     * signPhotoUrl :
     * speedAverage : 0
     * status : 1
     * timesOfWork : 0
     * title : 院长
     * trueName : 于医生
     * updateAdmitResult : 1
     * username :
     * vcode : 13fadc33790b499e8d27612e2f73b111
     */

    private List<DataEntity> data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public int getStatus() {
        return status;
    }

    public long getTime() {
        return time;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String areaId;
        private int attitudeAverage;
        private String avatarUrl;
        private String certificate1Number;
        private String certificate1PhotoUrl;
        private String certificate2Number;
        private String certificate2PhotoUrl;
        private String channel;
        private int cityId;
        private Object ctime;
        private int disturbState;
        private int easemobId;
        private String easemobName;
        private int evaluation;
        private String hospital;
        private int hospitalId;
        private String icWithHandPhotoUrl;
        private int id;
        private String identifyNumber;
        private String identityCard;
        private int level;
        private long modiTime;
        private String noCertifyHospital;
        private String password;
        private String personPhotoUrl;
        private String phone;
        private int qualification;
        private int qualityAverage;
        private String sign;
        private String signPhotoUrl;
        private int speedAverage;
        private int status;
        private int timesOfWork;
        private String title;
        private String trueName;
        private int updateAdmitResult;
        private String username;
        private String vcode;

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public void setAttitudeAverage(int attitudeAverage) {
            this.attitudeAverage = attitudeAverage;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public void setCertificate1Number(String certificate1Number) {
            this.certificate1Number = certificate1Number;
        }

        public void setCertificate1PhotoUrl(String certificate1PhotoUrl) {
            this.certificate1PhotoUrl = certificate1PhotoUrl;
        }

        public void setCertificate2Number(String certificate2Number) {
            this.certificate2Number = certificate2Number;
        }

        public void setCertificate2PhotoUrl(String certificate2PhotoUrl) {
            this.certificate2PhotoUrl = certificate2PhotoUrl;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public void setCtime(Object ctime) {
            this.ctime = ctime;
        }

        public void setDisturbState(int disturbState) {
            this.disturbState = disturbState;
        }

        public void setEasemobId(int easemobId) {
            this.easemobId = easemobId;
        }

        public void setEasemobName(String easemobName) {
            this.easemobName = easemobName;
        }

        public void setEvaluation(int evaluation) {
            this.evaluation = evaluation;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }

        public void setIcWithHandPhotoUrl(String icWithHandPhotoUrl) {
            this.icWithHandPhotoUrl = icWithHandPhotoUrl;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setIdentifyNumber(String identifyNumber) {
            this.identifyNumber = identifyNumber;
        }

        public void setIdentityCard(String identityCard) {
            this.identityCard = identityCard;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void setModiTime(long modiTime) {
            this.modiTime = modiTime;
        }

        public void setNoCertifyHospital(String noCertifyHospital) {
            this.noCertifyHospital = noCertifyHospital;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setPersonPhotoUrl(String personPhotoUrl) {
            this.personPhotoUrl = personPhotoUrl;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setQualification(int qualification) {
            this.qualification = qualification;
        }

        public void setQualityAverage(int qualityAverage) {
            this.qualityAverage = qualityAverage;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public void setSignPhotoUrl(String signPhotoUrl) {
            this.signPhotoUrl = signPhotoUrl;
        }

        public void setSpeedAverage(int speedAverage) {
            this.speedAverage = speedAverage;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setTimesOfWork(int timesOfWork) {
            this.timesOfWork = timesOfWork;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTrueName(String trueName) {
            this.trueName = trueName;
        }

        public void setUpdateAdmitResult(int updateAdmitResult) {
            this.updateAdmitResult = updateAdmitResult;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setVcode(String vcode) {
            this.vcode = vcode;
        }

        public String getAreaId() {
            return areaId;
        }

        public int getAttitudeAverage() {
            return attitudeAverage;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public String getCertificate1Number() {
            return certificate1Number;
        }

        public String getCertificate1PhotoUrl() {
            return certificate1PhotoUrl;
        }

        public String getCertificate2Number() {
            return certificate2Number;
        }

        public String getCertificate2PhotoUrl() {
            return certificate2PhotoUrl;
        }

        public String getChannel() {
            return channel;
        }

        public int getCityId() {
            return cityId;
        }

        public Object getCtime() {
            return ctime;
        }

        public int getDisturbState() {
            return disturbState;
        }

        public int getEasemobId() {
            return easemobId;
        }

        public String getEasemobName() {
            return easemobName;
        }

        public int getEvaluation() {
            return evaluation;
        }

        public String getHospital() {
            return hospital;
        }

        public int getHospitalId() {
            return hospitalId;
        }

        public String getIcWithHandPhotoUrl() {
            return icWithHandPhotoUrl;
        }

        public int getId() {
            return id;
        }

        public String getIdentifyNumber() {
            return identifyNumber;
        }

        public String getIdentityCard() {
            return identityCard;
        }

        public int getLevel() {
            return level;
        }

        public long getModiTime() {
            return modiTime;
        }

        public String getNoCertifyHospital() {
            return noCertifyHospital;
        }

        public String getPassword() {
            return password;
        }

        public String getPersonPhotoUrl() {
            return personPhotoUrl;
        }

        public String getPhone() {
            return phone;
        }

        public int getQualification() {
            return qualification;
        }

        public int getQualityAverage() {
            return qualityAverage;
        }

        public String getSign() {
            return sign;
        }

        public String getSignPhotoUrl() {
            return signPhotoUrl;
        }

        public int getSpeedAverage() {
            return speedAverage;
        }

        public int getStatus() {
            return status;
        }

        public int getTimesOfWork() {
            return timesOfWork;
        }

        public String getTitle() {
            return title;
        }

        public String getTrueName() {
            return trueName;
        }

        public int getUpdateAdmitResult() {
            return updateAdmitResult;
        }

        public String getUsername() {
            return username;
        }

        public String getVcode() {
            return vcode;
        }
    }
}
