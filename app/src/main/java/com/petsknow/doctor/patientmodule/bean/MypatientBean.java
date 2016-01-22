package com.petsknow.doctor.patientmodule.bean;

import java.util.List;

/**
 * Created by yukuo on 2016/1/22.
 */
public class MypatientBean {

    /**
     * data : [{"advice":"看看去","age":0,"birthday":1449137409000,"ctime":1449563132000,"descPhotos":"","doctorId":57,"doctorName":"于医生","gender":0,"id":901,"illnessAnalysis":"啦啦啦","illnessDescription":"告诉身体","insideDebugTime":1448985600000,"isNeuter":1,"judgeId":0,"medicalRecordUrl":"","multiVaccineTime":1449072000000,"name":"","neuter":null,"outsideDebugTime":0,"ownerId":156,"petId":15,"pets":{"age":0,"birthday":1449137409000,"ctime":null,"gender":2,"id":15,"imageBackground":"11561449225947823","insideDebugTime":1448985600000,"isInsideDebug":2,"isMultiVaccine":2,"isNeuter":1,"isOutsideDebug":2,"isRabiesVaccine":3,"multiVaccineTime":1449072000000,"name":"测试1","neuter":null,"outsideDebugTime":0,"petsAvator":"android1561449137407335","rabiesVaccineTime":null,"species":2,"status":1,"updateTime":1449137410000,"userId":156,"variety":48},"rabiesVaccineTime":null,"registerId":1268,"sessionId":0,"species":2,"status":1,"variety":48},{"advice":"lock","age":0,"birthday":1449489079000,"ctime":1449563049000,"descPhotos":"","doctorId":57,"doctorName":"于医生","gender":0,"id":900,"illnessAnalysis":"lock","illnessDescription":"就好","insideDebugTime":1446825600000,"isNeuter":1,"judgeId":0,"medicalRecordUrl":"","multiVaccineTime":1449417600000,"name":"","neuter":null,"outsideDebugTime":1446825600000,"ownerId":156,"petId":28,"pets":{"age":0,"birthday":1449489079000,"ctime":null,"gender":2,"id":28,"imageBackground":"11561449559275743","insideDebugTime":1446825600000,"isInsideDebug":2,"isMultiVaccine":2,"isNeuter":1,"isOutsideDebug":2,"isRabiesVaccine":2,"multiVaccineTime":1449417600000,"name":"hhg","neuter":null,"outsideDebugTime":1446825600000,"petsAvator":"android1561449489078239","rabiesVaccineTime":1449417600000,"species":2,"status":1,"updateTime":1449489080000,"userId":156,"variety":48},"rabiesVaccineTime":1449417600000,"registerId":1267,"sessionId":0,"species":2,"status":1,"variety":48},{"advice":"5168","age":0,"birthday":1449137409000,"ctime":1449562965000,"descPhotos":"","doctorId":57,"doctorName":"于医生","gender":0,"id":899,"illnessAnalysis":"拒绝","illnessDescription":"好就行奖学金","insideDebugTime":1448985600000,"isNeuter":1,"judgeId":0,"medicalRecordUrl":"","multiVaccineTime":1449072000000,"name":"","neuter":null,"outsideDebugTime":0,"ownerId":156,"petId":15,"pets":{"age":0,"birthday":1449137409000,"ctime":null,"gender":2,"id":15,"imageBackground":"11561449225947823","insideDebugTime":1448985600000,"isInsideDebug":2,"isMultiVaccine":2,"isNeuter":1,"isOutsideDebug":2,"isRabiesVaccine":3,"multiVaccineTime":1449072000000,"name":"测试1","neuter":null,"outsideDebugTime":0,"petsAvator":"android1561449137407335","rabiesVaccineTime":null,"species":2,"status":1,"updateTime":1449137410000,"userId":156,"variety":48},"rabiesVaccineTime":null,"registerId":1266,"sessionId":0,"species":2,"status":1,"variety":48}]
     * msg : success
     * status : 0
     * time : 1453436542996
     */

    private String msg;
    private int status;
    private long time;
    /**
     * advice : 看看去
     * age : 0
     * birthday : 1449137409000
     * ctime : 1449563132000
     * descPhotos :
     * doctorId : 57
     * doctorName : 于医生
     * gender : 0
     * id : 901
     * illnessAnalysis : 啦啦啦
     * illnessDescription : 告诉身体
     * insideDebugTime : 1448985600000
     * isNeuter : 1
     * judgeId : 0
     * medicalRecordUrl :
     * multiVaccineTime : 1449072000000
     * name :
     * neuter : null
     * outsideDebugTime : 0
     * ownerId : 156
     * petId : 15
     * pets : {"age":0,"birthday":1449137409000,"ctime":null,"gender":2,"id":15,"imageBackground":"11561449225947823","insideDebugTime":1448985600000,"isInsideDebug":2,"isMultiVaccine":2,"isNeuter":1,"isOutsideDebug":2,"isRabiesVaccine":3,"multiVaccineTime":1449072000000,"name":"测试1","neuter":null,"outsideDebugTime":0,"petsAvator":"android1561449137407335","rabiesVaccineTime":null,"species":2,"status":1,"updateTime":1449137410000,"userId":156,"variety":48}
     * rabiesVaccineTime : null
     * registerId : 1268
     * sessionId : 0
     * species : 2
     * status : 1
     * variety : 48
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
        private String advice;
        private int age;
        private long birthday;
        private long ctime;
        private String descPhotos;
        private int doctorId;
        private String doctorName;
        private int gender;
        private int id;
        private String illnessAnalysis;
        private String illnessDescription;
        private long insideDebugTime;
        private int isNeuter;
        private int judgeId;
        private String medicalRecordUrl;
        private long multiVaccineTime;
        private String name;
        private Object neuter;
        private long outsideDebugTime;
        private int ownerId;
        private int petId;
        /**
         * age : 0
         * birthday : 1449137409000
         * ctime : null
         * gender : 2
         * id : 15
         * imageBackground : 11561449225947823
         * insideDebugTime : 1448985600000
         * isInsideDebug : 2
         * isMultiVaccine : 2
         * isNeuter : 1
         * isOutsideDebug : 2
         * isRabiesVaccine : 3
         * multiVaccineTime : 1449072000000
         * name : 测试1
         * neuter : null
         * outsideDebugTime : 0
         * petsAvator : android1561449137407335
         * rabiesVaccineTime : null
         * species : 2
         * status : 1
         * updateTime : 1449137410000
         * userId : 156
         * variety : 48
         */

        private PetsEntity pets;
        private long rabiesVaccineTime;
        private int registerId;
        private int sessionId;
        private int species;
        private int status;
        private int variety;

        public void setAdvice(String advice) {
            this.advice = advice;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public void setDescPhotos(String descPhotos) {
            this.descPhotos = descPhotos;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setIllnessAnalysis(String illnessAnalysis) {
            this.illnessAnalysis = illnessAnalysis;
        }

        public void setIllnessDescription(String illnessDescription) {
            this.illnessDescription = illnessDescription;
        }

        public void setInsideDebugTime(long insideDebugTime) {
            this.insideDebugTime = insideDebugTime;
        }

        public void setIsNeuter(int isNeuter) {
            this.isNeuter = isNeuter;
        }

        public void setJudgeId(int judgeId) {
            this.judgeId = judgeId;
        }

        public void setMedicalRecordUrl(String medicalRecordUrl) {
            this.medicalRecordUrl = medicalRecordUrl;
        }

        public void setMultiVaccineTime(long multiVaccineTime) {
            this.multiVaccineTime = multiVaccineTime;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNeuter(Object neuter) {
            this.neuter = neuter;
        }

        public void setOutsideDebugTime(long outsideDebugTime) {
            this.outsideDebugTime = outsideDebugTime;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public void setPetId(int petId) {
            this.petId = petId;
        }

        public void setPets(PetsEntity pets) {
            this.pets = pets;
        }

        public void setRabiesVaccineTime(long rabiesVaccineTime) {
            this.rabiesVaccineTime = rabiesVaccineTime;
        }

        public void setRegisterId(int registerId) {
            this.registerId = registerId;
        }

        public void setSessionId(int sessionId) {
            this.sessionId = sessionId;
        }

        public void setSpecies(int species) {
            this.species = species;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setVariety(int variety) {
            this.variety = variety;
        }

        public String getAdvice() {
            return advice;
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

        public String getDescPhotos() {
            return descPhotos;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public int getGender() {
            return gender;
        }

        public int getId() {
            return id;
        }

        public String getIllnessAnalysis() {
            return illnessAnalysis;
        }

        public String getIllnessDescription() {
            return illnessDescription;
        }

        public long getInsideDebugTime() {
            return insideDebugTime;
        }

        public int getIsNeuter() {
            return isNeuter;
        }

        public int getJudgeId() {
            return judgeId;
        }

        public String getMedicalRecordUrl() {
            return medicalRecordUrl;
        }

        public long getMultiVaccineTime() {
            return multiVaccineTime;
        }

        public String getName() {
            return name;
        }

        public Object getNeuter() {
            return neuter;
        }

        public long getOutsideDebugTime() {
            return outsideDebugTime;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public int getPetId() {
            return petId;
        }

        public PetsEntity getPets() {
            return pets;
        }

        public long getRabiesVaccineTime() {
            return rabiesVaccineTime;
        }

        public int getRegisterId() {
            return registerId;
        }

        public int getSessionId() {
            return sessionId;
        }

        public int getSpecies() {
            return species;
        }

        public int getStatus() {
            return status;
        }

        public int getVariety() {
            return variety;
        }

        public static class PetsEntity {
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
            private Object neuter;
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

            public void setNeuter(Object neuter) {
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

            public Object getNeuter() {
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
