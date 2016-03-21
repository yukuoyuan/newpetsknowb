package com.petsknow.doctor.patientmodule.bean;

import java.util.List;

/**
 * Created by yukuo on 2016/3/21.
 */
public class ListInquryListBean {

    /**
     * data : [{"acceptTime":1458478722000,"avatarUrl":"","billStatus":2,"creatingTime":1458465900000,"description":"请求帮助","doctorEasemobName":"","doctorId":21,"easemobName":"","id":893,"illnessCaseId":0,"ownerId":156,"petsId":132,"petsVo":{"age":1,"birthday":null,"ctime":null,"gender":2,"id":132,"imageBackground":"","insideDebugTime":null,"isInsideDebug":0,"isMultiVaccine":0,"isNeuter":1,"isOutsideDebug":0,"isRabiesVaccine":0,"multiVaccineTime":null,"name":"豆豆","neuter":null,"outsideDebugTime":null,"petsAvator":"android1561444883863831","rabiesVaccineTime":null,"species":2,"status":0,"updateTime":1444883865000,"userId":156,"variety":1},"photos":["15614584658992040","15614584658992041"],"sessionId":0,"status":1,"userName":""},{"acceptTime":null,"avatarUrl":"","billStatus":4,"creatingTime":1458465387000,"description":"流浪狗，身上不知道长了什么，不能走路了，","doctorEasemobName":"","doctorId":0,"easemobName":"","id":892,"illnessCaseId":0,"ownerId":156,"petsId":132,"petsVo":{"age":1,"birthday":null,"ctime":null,"gender":2,"id":132,"imageBackground":"","insideDebugTime":null,"isInsideDebug":0,"isMultiVaccine":0,"isNeuter":1,"isOutsideDebug":0,"isRabiesVaccine":0,"multiVaccineTime":null,"name":"豆豆","neuter":null,"outsideDebugTime":null,"petsAvator":"android1561444883863831","rabiesVaccineTime":null,"species":2,"status":0,"updateTime":1444883865000,"userId":156,"variety":1},"photos":["15614584653858650","15614584653858651"],"sessionId":0,"status":1,"userName":""},{"acceptTime":1448150793000,"avatarUrl":"","billStatus":6,"creatingTime":1448025396000,"description":"毛喜欢打结","doctorEasemobName":"","doctorId":21,"easemobName":"","id":438,"illnessCaseId":0,"ownerId":156,"petsId":132,"petsVo":{"age":1,"birthday":null,"ctime":null,"gender":2,"id":132,"imageBackground":"","insideDebugTime":null,"isInsideDebug":0,"isMultiVaccine":0,"isNeuter":1,"isOutsideDebug":0,"isRabiesVaccine":0,"multiVaccineTime":null,"name":"豆豆","neuter":null,"outsideDebugTime":null,"petsAvator":"android1561444883863831","rabiesVaccineTime":null,"species":2,"status":0,"updateTime":1444883865000,"userId":156,"variety":1},"photos":[],"sessionId":0,"status":1,"userName":""},{"acceptTime":1444884060000,"avatarUrl":"","billStatus":6,"creatingTime":1444883935000,"description":"昨天一天没吃饭，今天的话就吃了一点猪肝，然后的话早上拉了粑粑，刚刚还吐了一下。吐的水。","doctorEasemobName":"","doctorId":21,"easemobName":"","id":236,"illnessCaseId":0,"ownerId":156,"petsId":132,"petsVo":{"age":1,"birthday":null,"ctime":null,"gender":2,"id":132,"imageBackground":"","insideDebugTime":null,"isInsideDebug":0,"isMultiVaccine":0,"isNeuter":1,"isOutsideDebug":0,"isRabiesVaccine":0,"multiVaccineTime":null,"name":"豆豆","neuter":null,"outsideDebugTime":null,"petsAvator":"android1561444883863831","rabiesVaccineTime":null,"species":2,"status":0,"updateTime":1444883865000,"userId":156,"variety":1},"photos":[],"sessionId":0,"status":1,"userName":""}]
     * msg : success
     * status : 0
     * time : 1458526876943
     */

    private String msg;
    private int status;
    private long time;
    /**
     * acceptTime : 1458478722000
     * avatarUrl :
     * billStatus : 2
     * creatingTime : 1458465900000
     * description : 请求帮助
     * doctorEasemobName :
     * doctorId : 21
     * easemobName :
     * id : 893
     * illnessCaseId : 0
     * ownerId : 156
     * petsId : 132
     * petsVo : {"age":1,"birthday":null,"ctime":null,"gender":2,"id":132,"imageBackground":"","insideDebugTime":null,"isInsideDebug":0,"isMultiVaccine":0,"isNeuter":1,"isOutsideDebug":0,"isRabiesVaccine":0,"multiVaccineTime":null,"name":"豆豆","neuter":null,"outsideDebugTime":null,"petsAvator":"android1561444883863831","rabiesVaccineTime":null,"species":2,"status":0,"updateTime":1444883865000,"userId":156,"variety":1}
     * photos : ["15614584658992040","15614584658992041"]
     * sessionId : 0
     * status : 1
     * userName :
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
        private long acceptTime;
        private String avatarUrl;
        private int billStatus;
        private long creatingTime;
        private String description;
        private String doctorEasemobName;
        private int doctorId;
        private String easemobName;
        private int id;
        private int illnessCaseId;
        private int ownerId;
        private int petsId;
        /**
         * age : 1
         * birthday : null
         * ctime : null
         * gender : 2
         * id : 132
         * imageBackground :
         * insideDebugTime : null
         * isInsideDebug : 0
         * isMultiVaccine : 0
         * isNeuter : 1
         * isOutsideDebug : 0
         * isRabiesVaccine : 0
         * multiVaccineTime : null
         * name : 豆豆
         * neuter : null
         * outsideDebugTime : null
         * petsAvator : android1561444883863831
         * rabiesVaccineTime : null
         * species : 2
         * status : 0
         * updateTime : 1444883865000
         * userId : 156
         * variety : 1
         */

        private PetsVoEntity petsVo;
        private int sessionId;
        private int status;
        private String userName;
        private List<String> photos;

        public void setAcceptTime(long acceptTime) {
            this.acceptTime = acceptTime;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public void setBillStatus(int billStatus) {
            this.billStatus = billStatus;
        }

        public void setCreatingTime(long creatingTime) {
            this.creatingTime = creatingTime;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setDoctorEasemobName(String doctorEasemobName) {
            this.doctorEasemobName = doctorEasemobName;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public void setEasemobName(String easemobName) {
            this.easemobName = easemobName;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setIllnessCaseId(int illnessCaseId) {
            this.illnessCaseId = illnessCaseId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public void setPetsId(int petsId) {
            this.petsId = petsId;
        }

        public void setPetsVo(PetsVoEntity petsVo) {
            this.petsVo = petsVo;
        }

        public void setSessionId(int sessionId) {
            this.sessionId = sessionId;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setPhotos(List<String> photos) {
            this.photos = photos;
        }

        public long getAcceptTime() {
            return acceptTime;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public int getBillStatus() {
            return billStatus;
        }

        public long getCreatingTime() {
            return creatingTime;
        }

        public String getDescription() {
            return description;
        }

        public String getDoctorEasemobName() {
            return doctorEasemobName;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public String getEasemobName() {
            return easemobName;
        }

        public int getId() {
            return id;
        }

        public int getIllnessCaseId() {
            return illnessCaseId;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public int getPetsId() {
            return petsId;
        }

        public PetsVoEntity getPetsVo() {
            return petsVo;
        }

        public int getSessionId() {
            return sessionId;
        }

        public int getStatus() {
            return status;
        }

        public String getUserName() {
            return userName;
        }

        public List<String> getPhotos() {
            return photos;
        }

        public static class PetsVoEntity {
            private int age;
            private Object birthday;
            private Object ctime;
            private int gender;
            private int id;
            private String imageBackground;
            private Object insideDebugTime;
            private int isInsideDebug;
            private int isMultiVaccine;
            private int isNeuter;
            private int isOutsideDebug;
            private int isRabiesVaccine;
            private Object multiVaccineTime;
            private String name;
            private Object neuter;
            private Object outsideDebugTime;
            private String petsAvator;
            private Object rabiesVaccineTime;
            private int species;
            private int status;
            private long updateTime;
            private int userId;
            private int variety;

            public void setAge(int age) {
                this.age = age;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public void setCtime(Object ctime) {
                this.ctime = ctime;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setImageBackground(String imageBackground) {
                this.imageBackground = imageBackground;
            }

            public void setInsideDebugTime(Object insideDebugTime) {
                this.insideDebugTime = insideDebugTime;
            }

            public void setIsInsideDebug(int isInsideDebug) {
                this.isInsideDebug = isInsideDebug;
            }

            public void setIsMultiVaccine(int isMultiVaccine) {
                this.isMultiVaccine = isMultiVaccine;
            }

            public void setIsNeuter(int isNeuter) {
                this.isNeuter = isNeuter;
            }

            public void setIsOutsideDebug(int isOutsideDebug) {
                this.isOutsideDebug = isOutsideDebug;
            }

            public void setIsRabiesVaccine(int isRabiesVaccine) {
                this.isRabiesVaccine = isRabiesVaccine;
            }

            public void setMultiVaccineTime(Object multiVaccineTime) {
                this.multiVaccineTime = multiVaccineTime;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setNeuter(Object neuter) {
                this.neuter = neuter;
            }

            public void setOutsideDebugTime(Object outsideDebugTime) {
                this.outsideDebugTime = outsideDebugTime;
            }

            public void setPetsAvator(String petsAvator) {
                this.petsAvator = petsAvator;
            }

            public void setRabiesVaccineTime(Object rabiesVaccineTime) {
                this.rabiesVaccineTime = rabiesVaccineTime;
            }

            public void setSpecies(int species) {
                this.species = species;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public void setVariety(int variety) {
                this.variety = variety;
            }

            public int getAge() {
                return age;
            }

            public Object getBirthday() {
                return birthday;
            }

            public Object getCtime() {
                return ctime;
            }

            public int getGender() {
                return gender;
            }

            public int getId() {
                return id;
            }

            public String getImageBackground() {
                return imageBackground;
            }

            public Object getInsideDebugTime() {
                return insideDebugTime;
            }

            public int getIsInsideDebug() {
                return isInsideDebug;
            }

            public int getIsMultiVaccine() {
                return isMultiVaccine;
            }

            public int getIsNeuter() {
                return isNeuter;
            }

            public int getIsOutsideDebug() {
                return isOutsideDebug;
            }

            public int getIsRabiesVaccine() {
                return isRabiesVaccine;
            }

            public Object getMultiVaccineTime() {
                return multiVaccineTime;
            }

            public String getName() {
                return name;
            }

            public Object getNeuter() {
                return neuter;
            }

            public Object getOutsideDebugTime() {
                return outsideDebugTime;
            }

            public String getPetsAvator() {
                return petsAvator;
            }

            public Object getRabiesVaccineTime() {
                return rabiesVaccineTime;
            }

            public int getSpecies() {
                return species;
            }

            public int getStatus() {
                return status;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public int getUserId() {
                return userId;
            }

            public int getVariety() {
                return variety;
            }
        }
    }
}
