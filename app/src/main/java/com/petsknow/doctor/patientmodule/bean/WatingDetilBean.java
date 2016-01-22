package com.petsknow.doctor.patientmodule.bean;

import java.util.List;

/**
 * Created by yukuo on 2016/1/22.
 */
public class WatingDetilBean {

    /**
     * data : [{"acceptTime":1453301214000,"avatarUrl":"","billStatus":6,"creatingTime":1453301192000,"description":"幾級了","doctorId":39,"easemobName":"user_150","id":1442,"illnessCaseId":0,"ownerId":150,"petsId":92,"petsVo":{"age":0,"birthday":1452695658000,"ctime":null,"gender":2,"id":92,"imageBackground":"","insideDebugTime":0,"isInsideDebug":3,"isMultiVaccine":3,"isNeuter":3,"isOutsideDebug":3,"isRabiesVaccine":3,"multiVaccineTime":0,"name":"绝对是","neuter":0,"outsideDebugTime":0,"petsAvator":"1/150/1452695668195_0","rabiesVaccineTime":0,"species":1,"status":0,"updateTime":1452695670000,"userId":150,"variety":61},"photos":[],"sessionId":0,"status":1,"userName":"测试测试测试测试"}]
     * msg : success
     * status : 0
     * time : 1453458877952
     */

    private String msg;
    private int status;
    private long time;
    /**
     * acceptTime : 1453301214000
     * avatarUrl :
     * billStatus : 6
     * creatingTime : 1453301192000
     * description : 幾級了
     * doctorId : 39
     * easemobName : user_150
     * id : 1442
     * illnessCaseId : 0
     * ownerId : 150
     * petsId : 92
     * petsVo : {"age":0,"birthday":1452695658000,"ctime":null,"gender":2,"id":92,"imageBackground":"","insideDebugTime":0,"isInsideDebug":3,"isMultiVaccine":3,"isNeuter":3,"isOutsideDebug":3,"isRabiesVaccine":3,"multiVaccineTime":0,"name":"绝对是","neuter":0,"outsideDebugTime":0,"petsAvator":"1/150/1452695668195_0","rabiesVaccineTime":0,"species":1,"status":0,"updateTime":1452695670000,"userId":150,"variety":61}
     * photos : []
     * sessionId : 0
     * status : 1
     * userName : 测试测试测试测试
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
         * birthday : 1452695658000
         * ctime : null
         * gender : 2
         * id : 92
         * imageBackground :
         * insideDebugTime : 0
         * isInsideDebug : 3
         * isMultiVaccine : 3
         * isNeuter : 3
         * isOutsideDebug : 3
         * isRabiesVaccine : 3
         * multiVaccineTime : 0
         * name : 绝对是
         * neuter : 0
         * outsideDebugTime : 0
         * petsAvator : 1/150/1452695668195_0
         * rabiesVaccineTime : 0
         * species : 1
         * status : 0
         * updateTime : 1452695670000
         * userId : 150
         * variety : 61
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
            private int neuter;
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

            public void setNeuter(int neuter) {
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

            public int getNeuter() {
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
