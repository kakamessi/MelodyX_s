package com.angelmusic.student.infobean;

/**
 * Created by wangshuai on 2017/10/12.
 */

public class LoginBean {


    /**
     * code : 200
     * description : 请求成功
     * detail : {"classId":1,"phone":"05631011001","schoolId":4,"className":"1班","id":1,"stuName":"1","schoolName":"宣称十一小学"}
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
         * classId : 1
         * phone : 05631011001
         * schoolId : 4
         * className : 1班
         * id : 1
         * stuName : 1
         * schoolName : 宣称十一小学
         */

        private int classId;
        private String phone;
        private int schoolId;
        private String className;
        private int id;
        private String stuName;
        private String schoolName;

        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
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

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStuName() {
            return stuName;
        }

        public void setStuName(String stuName) {
            this.stuName = stuName;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }
    }
}
