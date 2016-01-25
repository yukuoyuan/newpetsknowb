package com.petsknow.doctor.mainmodule.bean;

import java.util.List;

/**
 * Created by yukuo on 2016/1/21.
 */
public class SeesionBean {

    /**
     * data : [{"acceptTime":1451274921000,"avatarUrl":"","billStatus":4,"creatingTime":1450683651000,"description":"啊么事天津","doctorId":57,"easemobName":"user_194","id":1301,"illnessCaseId":0,"ownerId":194,"petsId":68,"petsVo":{"age":0,"birthday":1450368000000,"ctime":null,"gender":1,"id":68,"imageBackground":"","insideDebugTime":null,"isInsideDebug":0,"isMultiVaccine":0,"isNeuter":0,"isOutsideDebug":0,"isRabiesVaccine":0,"multiVaccineTime":null,"name":"是啊样","neuter":0,"outsideDebugTime":null,"petsAvator":"","rabiesVaccineTime":null,"species":0,"status":0,"updateTime":1450683584000,"userId":194,"variety":65},"photos":["doctor/1450683638267.jpg","doctor/1450683639673.jpg","doctor/1450683640637.jpg"],"sessionId":0,"status":1,"userName":""},{"acceptTime":1451275881000,"avatarUrl":"","billStatus":4,"creatingTime":1450322009000,"description":"本土","doctorId":57,"easemobName":"user_194","id":1300,"illnessCaseId":0,"ownerId":194,"petsId":45,"petsVo":{"age":0,"birthday":1418227200000,"ctime":null,"gender":1,"id":45,"imageBackground":"","insideDebugTime":1449072000000,"isInsideDebug":0,"isMultiVaccine":0,"isNeuter":0,"isOutsideDebug":0,"isRabiesVaccine":0,"multiVaccineTime":1448899200000,"name":"策划四","neuter":0,"outsideDebugTime":1449158400000,"petsAvator":"","rabiesVaccineTime":1448985600000,"species":0,"status":0,"updateTime":1450089303000,"userId":194,"variety":69},"photos":["doctor/1450321990778.jpg","doctor/1450321991652.jpg"],"sessionId":0,"status":1,"userName":""},{"acceptTime":1449563094000,"avatarUrl":"","billStatus":6,"creatingTime":1449563076000,"description":"告诉身体","doctorId":57,"easemobName":"","id":1268,"illnessCaseId":0,"ownerId":156,"petsId":15,"petsVo":{"age":0,"birthday":1449137409000,"ctime":null,"gender":2,"id":15,"imageBackground":"11561449225947823","insideDebugTime":1448985600000,"isInsideDebug":2,"isMultiVaccine":2,"isNeuter":1,"isOutsideDebug":2,"isRabiesVaccine":3,"multiVaccineTime":1449072000000,"name":"测试1","neuter":null,"outsideDebugTime":0,"petsAvator":"android1561449137407335","rabiesVaccineTime":null,"species":2,"status":0,"updateTime":1449137410000,"userId":156,"variety":48},"photos":[],"sessionId":0,"status":1,"userName":""},{"acceptTime":1449563011000,"avatarUrl":"","billStatus":6,"creatingTime":1449562994000,"description":"就好","doctorId":57,"easemobName":"","id":1267,"illnessCaseId":0,"ownerId":156,"petsId":28,"petsVo":{"age":0,"birthday":1449489079000,"ctime":null,"gender":2,"id":28,"imageBackground":"11561449559275743","insideDebugTime":1446825600000,"isInsideDebug":2,"isMultiVaccine":2,"isNeuter":1,"isOutsideDebug":2,"isRabiesVaccine":2,"multiVaccineTime":1449417600000,"name":"hhg","neuter":null,"outsideDebugTime":1446825600000,"petsAvator":"android1561449489078239","rabiesVaccineTime":1449417600000,"species":2,"status":0,"updateTime":1449489080000,"userId":156,"variety":48},"photos":[],"sessionId":0,"status":1,"userName":""},{"acceptTime":1449562918000,"avatarUrl":"","billStatus":6,"creatingTime":1449562883000,"description":"好就行奖学金","doctorId":57,"easemobName":"","id":1266,"illnessCaseId":0,"ownerId":156,"petsId":15,"petsVo":{"age":0,"birthday":1449137409000,"ctime":null,"gender":2,"id":15,"imageBackground":"11561449225947823","insideDebugTime":1448985600000,"isInsideDebug":2,"isMultiVaccine":2,"isNeuter":1,"isOutsideDebug":2,"isRabiesVaccine":3,"multiVaccineTime":1449072000000,"name":"测试1","neuter":null,"outsideDebugTime":0,"petsAvator":"android1561449137407335","rabiesVaccineTime":null,"species":2,"status":0,"updateTime":1449137410000,"userId":156,"variety":48},"photos":["15614495628826740","15614495628826741","15614495628826742"],"sessionId":0,"status":1,"userName":""}]
     * msg : success
     * status : 0
     * time : 1453371919128
     */

    private String msg;
    private int status;
    private long time;
    /**
     * acceptTime : 1451274921000
     * avatarUrl :
     * billStatus : 4
     * creatingTime : 1450683651000
     * description : 啊么事天津
     * doctorId : 57
     * easemobName : user_194
     * id : 1301
     * illnessCaseId : 0
     * ownerId : 194
     * petsId : 68
     * petsVo : {"age":0,"birthday":1450368000000,"ctime":null,"gender":1,"id":68,"imageBackground":"","insideDebugTime":null,"isInsideDebug":0,"isMultiVaccine":0,"isNeuter":0,"isOutsideDebug":0,"isRabiesVaccine":0,"multiVaccineTime":null,"name":"是啊样","neuter":0,"outsideDebugTime":null,"petsAvator":"","rabiesVaccineTime":null,"species":0,"status":0,"updateTime":1450683584000,"userId":194,"variety":65}
     * photos : ["doctor/1450683638267.jpg","doctor/1450683639673.jpg","doctor/1450683640637.jpg"]
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
        private int doctorId;
        private String easemobName;
        private int id;
        private int illnessCaseId;
        private int ownerId;
        private int petsId;
        /**
         * age : 0
         * birthday : 1450368000000
         * ctime : null
         * gender : 1
         * id : 68
         * imageBackground :
         * insideDebugTime : null
         * isInsideDebug : 0
         * isMultiVaccine : 0
         * isNeuter : 0
         * isOutsideDebug : 0
         * isRabiesVaccine : 0
         * multiVaccineTime : null
         * name : 是啊样
         * neuter : 0
         * outsideDebugTime : null
         * petsAvator :
         * rabiesVaccineTime : null
         * species : 0
         * status : 0
         * updateTime : 1450683584000
         * userId : 194
         * variety : 65
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
            private long birthday;
            private long ctime;
            private int gender;
            private int id;
            private String imageBackground;
            private long insideDebugTime;
            private int isInsideDebug;
            private int isMultiVaccine;
            private int isNeuter;
            private int isOutsideDebug;
            private int isRabiesVaccine;
            private long multiVaccineTime;
            private String name;
            private long neuter;
            private long outsideDebugTime;
            private String petsAvator;
            private long rabiesVaccineTime;
            private int species;
            private int status;
            private long updateTime;
            private int userId;
            private int variety;

            public void setAge(int age) {
                this.age = age;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public void setCtime(long ctime) {
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

            public void setInsideDebugTime(long insideDebugTime) {
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

            public void setMultiVaccineTime(long multiVaccineTime) {
                this.multiVaccineTime = multiVaccineTime;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setNeuter(long neuter) {
                this.neuter = neuter;
            }

            public void setOutsideDebugTime(long outsideDebugTime) {
                this.outsideDebugTime = outsideDebugTime;
            }

            public void setPetsAvator(String petsAvator) {
                this.petsAvator = petsAvator;
            }

            public void setRabiesVaccineTime(long rabiesVaccineTime) {
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

            public long getBirthday() {
                return birthday;
            }

            public long getCtime() {
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

            public long getInsideDebugTime() {
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

            public long getMultiVaccineTime() {
                return multiVaccineTime;
            }

            public String getName() {
                return name;
            }

            public long getNeuter() {
                return neuter;
            }

            public long getOutsideDebugTime() {
                return outsideDebugTime;
            }

            public String getPetsAvator() {
                return petsAvator;
            }

            public long getRabiesVaccineTime() {
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
