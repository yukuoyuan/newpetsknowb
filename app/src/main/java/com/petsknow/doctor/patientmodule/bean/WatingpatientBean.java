package com.petsknow.doctor.patientmodule.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yukuo on 2016/1/22.
 */
public class WatingpatientBean {
    private List<DataEntity> data;

    private String msg;

    private int status;

    private long time;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return this.data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return this.time;
    }

    public class DataEntity {
        private long acceptTime;

        private String avatarUrl;

        private int billStatus;

        private long creatingTime;

        private String description;

        private int doctorId;

        private String easemobName;

        private int id;

        private int ownerId;

        private int petsId;

        private petsVos petsVo;

        private List<String> photoss;

        private int sessionId;

        private int status;

        private String userName;

        public void setAcceptTime(long acceptTime) {
            this.acceptTime = acceptTime;
        }

        public long getAcceptTime() {
            return this.acceptTime;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getAvatarUrl() {
            return this.avatarUrl;
        }

        public void setBillStatus(int billStatus) {
            this.billStatus = billStatus;
        }

        public int getBillStatus() {
            return this.billStatus;
        }

        public void setCreatingTime(long creatingTime) {
            this.creatingTime = creatingTime;
        }

        public long getCreatingTime() {
            return this.creatingTime;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public int getDoctorId() {
            return this.doctorId;
        }

        public void setEasemobName(String easemobName) {
            this.easemobName = easemobName;
        }

        public String getEasemobName() {
            return this.easemobName;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public int getOwnerId() {
            return this.ownerId;
        }

        public void setPetsId(int petsId) {
            this.petsId = petsId;
        }

        public int getPetsId() {
            return this.petsId;
        }

        public void setPetsVo(petsVos petsVo) {
            this.petsVo = petsVo;
        }

        public petsVos getPetsVo() {
            return this.petsVo;
        }

        public void setPhotos(List<String> photos) {
            this.photoss = photos;
        }

        public List<String> getPhotos() {
            return this.photoss;
        }

        public void setSessionId(int sessionId) {
            this.sessionId = sessionId;
        }

        public int getSessionId() {
            return this.sessionId;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return this.status;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserName() {
            return this.userName;
        }

        public class petsVos {
            private int age;

            private long birthday;

            private int gender;

            private int id;

            private long insideDebugTime;

            private int isNeuter;

            private long multiVaccineTime;

            private String name;

            private long neuter;

            private long outsideDebugTime;

            private String petsAvator;


            private int species;

            private int status;

            private long updateTime;

            private int userId;

            private int variety;

            public petsVos() {
                super();
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getAge() {
                return this.age;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public long getBirthday() {
                return this.birthday;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getGender() {
                return this.gender;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getId() {
                return this.id;
            }

            public void setInsideDebugTime(long insideDebugTime) {
                this.insideDebugTime = insideDebugTime;
            }

            public long getInsideDebugTime() {
                return this.insideDebugTime;
            }

            public void setIsNeuter(int isNeuter) {
                this.isNeuter = isNeuter;
            }

            public int getIsNeuter() {
                return this.isNeuter;
            }

            public void setMultiVaccineTime(long multiVaccineTime) {
                this.multiVaccineTime = multiVaccineTime;
            }

            public long getMultiVaccineTime() {
                return this.multiVaccineTime;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return this.name;
            }

            public void setNeuter(long neuter) {
                this.neuter = neuter;
            }

            public long getNeuter() {
                return this.neuter;
            }

            public void setOutsideDebugTime(long outsideDebugTime) {
                this.outsideDebugTime = outsideDebugTime;
            }

            public long getOutsideDebugTime() {
                return this.outsideDebugTime;
            }

            public void setPetsAvator(String petsAvator) {
                this.petsAvator = petsAvator;
            }

            public String getPetsAvator() {
                return this.petsAvator;
            }

            public void setSpecies(int species) {
                this.species = species;
            }

            public int getSpecies() {
                return this.species;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getStatus() {
                return this.status;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public long getUpdateTime() {
                return this.updateTime;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getUserId() {
                return this.userId;
            }

            public void setVariety(int variety) {
                this.variety = variety;
            }

            public int getVariety() {
                return this.variety;
            }

        }

    }
}
