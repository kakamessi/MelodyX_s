package com.angelmusic.student.infobean;

/**
 * Created by wangshuai on 2017/10/12.
 */

public class LoginBean {


    /**
     * code : 200
     * description : 请求成功
     * detail : {"address":"北京","age":12,"classId":100,"className":null,"entrancetime":"2017-09-13 00:00:00","gender":1,"id":334366,"name":"3","phone":"13910000013","schoolId":293,"state":1,"studentNo":"3"}
     */

    private int code;
    private String description;
    private DetailLoginBean detail;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DetailLoginBean getDetail() {
        return detail;
    }

    public void setDetail(DetailLoginBean detail) {
        this.detail = detail;
    }

    public static class DetailLoginBean {
        /**
         * address : 北京
         * age : 12
         * classId : 100
         * className : null
         * entrancetime : 2017-09-13 00:00:00
         * gender : 1
         * id : 334366
         * name : 3
         * phone : 13910000013
         * schoolId : 293
         * state : 1
         * studentNo : 3
         */

        private String address;
        private int age;
        private int classId;
        private Object className;
        private String entrancetime;
        private int gender;
        private int id;
        private String name;
        private String phone;
        private int schoolId;
        private int state;
        private String studentNo;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
        }

        public Object getClassName() {
            return className;
        }

        public void setClassName(Object className) {
            this.className = className;
        }

        public String getEntrancetime() {
            return entrancetime;
        }

        public void setEntrancetime(String entrancetime) {
            this.entrancetime = entrancetime;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getStudentNo() {
            return studentNo;
        }

        public void setStudentNo(String studentNo) {
            this.studentNo = studentNo;
        }
    }
}
