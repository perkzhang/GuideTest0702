package com.example.perk.guidetest0702;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by HuiWu on 2016/5/5.
 */
public class JSONModel {

    public static class Key implements Parcelable {
        @Override
        public String toString() {
            return "Key{" +
                    "newpwd='" + newpwd + '\'' +
                    ", pwd='" + pwd + '\'' +
                    ", oldpwd='" + oldpwd + '\'' +
                    '}';
        }

        /**
         * newpwd : null
         * pwd : FEIJd77aCK000000
         * oldpwd : FEIJde97CK000000
         */

        private String newpwd;
        private String pwd;
        private String oldpwd;

        public String getNewpwd() {
            return newpwd;
        }

        public void setNewpwd(String newpwd) {
            this.newpwd = newpwd;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getOldpwd() {
            return oldpwd;
        }

        public void setOldpwd(String oldpwd) {
            this.oldpwd = oldpwd;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.newpwd);
            dest.writeString(this.pwd);
            dest.writeString(this.oldpwd);
        }

        public Key() {
        }

        protected Key(Parcel in) {
            this.newpwd = in.readString();
            this.pwd = in.readString();
            this.oldpwd = in.readString();
        }

        public static final Parcelable.Creator<Key> CREATOR = new Parcelable.Creator<Key>() {
            @Override
            public Key createFromParcel(Parcel source) {
                return new Key(source);
            }

            @Override
            public Key[] newArray(int size) {
                return new Key[size];
            }
        };
    }

    /**
     * 电子锁信息对象
     */
    public static class LockInfo implements Parcelable {

        /**
         * isNewLock : false
         * isPowerOff : false
         * powerOffMsg :
         * locks : [{"id":1,"lockrfid":"537C2E4600E680","pwd":"FEIJd77aCK000000","lockmemo":"测试","lastpwd":"FEIJde97CK000000","companyid":152,"orgna_id":359,"company":"上海电子锁管理公司","orgna_name":"总部","isuse":false,"recordid":-1,"openpowerid":-1,"opencount":"0","createtime":"/Date(1462947000000)/"}]
         * openpowers : []
         * openpowersMemoHashList : []
         * lockOk : false
         * lockFieldList : [{"nfclock_companystepfields_nodeuuid":0,"nfclock_companysteps_nodeuuid":0,"datatype":"img","showname":"开锁图片","allstepshow":false,"createtime":"/Date(-62135596800000)/","fixedField":"fixedField"}]
         * loopFieldList : [{"nfclock_companystepfields_nodeuuid":0,"nfclock_companysteps_nodeuuid":0,"datatype":"img","showname":"开锁图片","allstepshow":false,"createtime":"/Date(-62135596800000)/","fixedField":"fixedField"}]
         * openFieldList : [{"nfclock_companystepfields_nodeuuid":0,"nfclock_companysteps_nodeuuid":0,"datatype":"img","showname":"开锁图片","allstepshow":false,"createtime":"/Date(-62135596800000)/","fixedField":"fixedField"}]
         * loopOk : false
         * openOk : true
         */

        private boolean isNewLock;
        private boolean isPowerOff;
        private String powerOffMsg;
        private boolean lockOk;
        private boolean loopOk;
        private boolean openOk;
        private boolean havesppower;
        private boolean reportOk;
        private boolean ycOk;
        /**
         * defaultPwd : null
         * newPwd : null
         */

        private String defaultPwd;
        private String newPwd;

        public boolean isYcOk() {
            return ycOk;
        }

        public void setYcOk(boolean ycOk) {
            this.ycOk = ycOk;
        }

        private String postion;
        private int recordid;
        private String recordtype;
        private String objectname;
        /**
         * id : 1
         * lockrfid : 537C2E4600E680
         * pwd : FEIJd77aCK000000
         * lockmemo : 测试
         * lastpwd : FEIJde97CK000000
         * companyid : 152
         * orgna_id : 359
         * company : 上海电子锁管理公司
         * orgna_name : 总部
         * isuse : false
         * recordid : -1
         * openpowerid : -1
         * opencount : 0
         * createtime : /Date(1462947000000)/
         */

        private LocksBean[] locks;
        private OpenPower[] openpowers;
        private PowerMemo[] openpowersMemoHashList;
        /**
         * nfclock_companystepfields_nodeuuid : 0
         * nfclock_companysteps_nodeuuid : 0
         * datatype : img
         * showname : 开锁图片
         * allstepshow : false
         * createtime : /Date(-62135596800000)/
         * fixedField : fixedField
         */

        private FieldListBean[] lockFieldList;
        /**
         * nfclock_companystepfields_nodeuuid : 0
         * nfclock_companysteps_nodeuuid : 0
         * datatype : img
         * showname : 开锁图片
         * allstepshow : false
         * createtime : /Date(-62135596800000)/
         * fixedField : fixedField
         */

        private FieldListBean[] loopFieldList;

        public boolean isReportOk() {
            return reportOk;
        }

        public void setReportOk(boolean reportOk) {
            this.reportOk = reportOk;
        }

        /**
         * nfclock_companystepfields_nodeuuid : 0
         * nfclock_companysteps_nodeuuid : 0
         * datatype : img
         * showname : 开锁图片
         * allstepshow : false
         * createtime : /Date(-62135596800000)/
         * fixedField : fixedField
         */

        private FieldListBean[] openFieldList;

        public boolean isIsNewLock() {
            return isNewLock;
        }

        public void setIsNewLock(boolean isNewLock) {
            this.isNewLock = isNewLock;
        }

        public boolean isIsPowerOff() {
            return isPowerOff;
        }

        public void setIsPowerOff(boolean isPowerOff) {
            this.isPowerOff = isPowerOff;
        }

        public String getPowerOffMsg() {
            return powerOffMsg;
        }

        public void setPowerOffMsg(String powerOffMsg) {
            this.powerOffMsg = powerOffMsg;
        }

        public boolean isLockOk() {
            return lockOk;
        }

        public void setLockOk(boolean lockOk) {
            this.lockOk = lockOk;
        }

        public boolean isLoopOk() {
            return loopOk;
        }

        public void setLoopOk(boolean loopOk) {
            this.loopOk = loopOk;
        }

        public boolean isOpenOk() {
            return openOk;
        }

        public boolean isHavesppower() {
            return havesppower;
        }

        public void setHavesppower(boolean havesppower) {
            this.havesppower = havesppower;
        }

        public void setOpenOk(boolean openOk) {
            this.openOk = openOk;
        }

        public LocksBean[] getLocks() {
            return locks;
        }

        public void setLocks(LocksBean[] locks) {
            this.locks = locks;
        }

        public OpenPower[] getOpenpowers() {
            return openpowers;
        }

        public void setOpenpowers(OpenPower[] openpowers) {
            this.openpowers = openpowers;
        }

        public PowerMemo[] getOpenpowersMemoHashList() {
            return openpowersMemoHashList;
        }

        public void setOpenpowersMemoHashList(PowerMemo[] openpowersMemoHashList) {
            this.openpowersMemoHashList = openpowersMemoHashList;
        }

        public FieldListBean[] getLockFieldList() {
            return lockFieldList;
        }

        public void setLockFieldList(FieldListBean[] lockFieldList) {
            this.lockFieldList = lockFieldList;
        }

        public FieldListBean[] getLoopFieldList() {
            return loopFieldList;
        }

        public void setLoopFieldList(FieldListBean[] loopFieldList) {
            this.loopFieldList = loopFieldList;
        }

        public FieldListBean[] getOpenFieldList() {
            return openFieldList;
        }

        public void setOpenFieldList(FieldListBean[] openFieldList) {
            this.openFieldList = openFieldList;
        }

        public String getDefaultPwd() {
            return defaultPwd;
        }

        public void setDefaultPwd(String defaultPwd) {
            this.defaultPwd = defaultPwd;
        }

        public String getNewPwd() {
            return newPwd;
        }

        public void setNewPwd(String newPwd) {
            this.newPwd = newPwd;
        }

        public static class LocksBean implements Parcelable {
            private int id;
            private String lockrfid;
            private String pwd;
            private String lockmemo;
            private String lastpwd;
            private int companyid;
            private int orgna_id;
            private String company;
            private String orgna_name;
            private boolean isuse;
            private int recordid;
            private int openpowerid;
            private String opencount;
            private String createtime;
            /**
             * distributionlock : false
             */

            private boolean distributionlock;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLockrfid() {
                return lockrfid;
            }

            public void setLockrfid(String lockrfid) {
                this.lockrfid = lockrfid;
            }

            public String getPwd() {
                return pwd;
            }

            public void setPwd(String pwd) {
                this.pwd = pwd;
            }

            public String getLockmemo() {
                return lockmemo;
            }

            public void setLockmemo(String lockmemo) {
                this.lockmemo = lockmemo;
            }

            public String getLastpwd() {
                return lastpwd;
            }

            public void setLastpwd(String lastpwd) {
                this.lastpwd = lastpwd;
            }

            public int getCompanyid() {
                return companyid;
            }

            public void setCompanyid(int companyid) {
                this.companyid = companyid;
            }

            public int getOrgna_id() {
                return orgna_id;
            }

            public void setOrgna_id(int orgna_id) {
                this.orgna_id = orgna_id;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getOrgna_name() {
                return orgna_name;
            }

            public void setOrgna_name(String orgna_name) {
                this.orgna_name = orgna_name;
            }

            public boolean isIsuse() {
                return isuse;
            }

            public void setIsuse(boolean isuse) {
                this.isuse = isuse;
            }

            public int getRecordid() {
                return recordid;
            }

            public void setRecordid(int recordid) {
                this.recordid = recordid;
            }

            public int getOpenpowerid() {
                return openpowerid;
            }

            public void setOpenpowerid(int openpowerid) {
                this.openpowerid = openpowerid;
            }

            public String getOpencount() {
                return opencount;
            }

            public void setOpencount(String opencount) {
                this.opencount = opencount;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public LocksBean() {
            }

            public boolean isDistributionlock() {
                return distributionlock;
            }

            public void setDistributionlock(boolean distributionlock) {
                this.distributionlock = distributionlock;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.lockrfid);
                dest.writeString(this.pwd);
                dest.writeString(this.lockmemo);
                dest.writeString(this.lastpwd);
                dest.writeInt(this.companyid);
                dest.writeInt(this.orgna_id);
                dest.writeString(this.company);
                dest.writeString(this.orgna_name);
                dest.writeByte(this.isuse ? (byte) 1 : (byte) 0);
                dest.writeInt(this.recordid);
                dest.writeInt(this.openpowerid);
                dest.writeString(this.opencount);
                dest.writeString(this.createtime);
                dest.writeByte(this.distributionlock ? (byte) 1 : (byte) 0);
            }

            protected LocksBean(Parcel in) {
                this.id = in.readInt();
                this.lockrfid = in.readString();
                this.pwd = in.readString();
                this.lockmemo = in.readString();
                this.lastpwd = in.readString();
                this.companyid = in.readInt();
                this.orgna_id = in.readInt();
                this.company = in.readString();
                this.orgna_name = in.readString();
                this.isuse = in.readByte() != 0;
                this.recordid = in.readInt();
                this.openpowerid = in.readInt();
                this.opencount = in.readString();
                this.createtime = in.readString();
                this.distributionlock = in.readByte() != 0;
            }

            public static final Creator<LocksBean> CREATOR = new Creator<LocksBean>() {
                @Override
                public LocksBean createFromParcel(Parcel source) {
                    return new LocksBean(source);
                }

                @Override
                public LocksBean[] newArray(int size) {
                    return new LocksBean[size];
                }
            };
        }


        public String getPostion() {
            return postion;
        }

        public void setPostion(String postion) {
            this.postion = postion;
        }

        public int getRecordid() {
            return recordid;
        }

        public void setRecordid(int recordid) {
            this.recordid = recordid;
        }

        public String getRecordtype() {
            return recordtype;
        }

        public void setRecordtype(String recordtype) {
            this.recordtype = recordtype;
        }

        public String getObjectname() {
            return objectname;
        }

        public void setObjectname(String objectname) {
            this.objectname = objectname;
        }

        public LockInfo() {
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.isNewLock ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isPowerOff ? (byte) 1 : (byte) 0);
            dest.writeString(this.powerOffMsg);
            dest.writeByte(this.lockOk ? (byte) 1 : (byte) 0);
            dest.writeByte(this.loopOk ? (byte) 1 : (byte) 0);
            dest.writeByte(this.openOk ? (byte) 1 : (byte) 0);
            dest.writeByte(this.havesppower ? (byte) 1 : (byte) 0);
            dest.writeByte(this.reportOk ? (byte) 1 : (byte) 0);
            dest.writeByte(this.ycOk ? (byte) 1 : (byte) 0);
            dest.writeString(this.postion);
            dest.writeInt(this.recordid);
            dest.writeString(this.recordtype);
            dest.writeString(this.objectname);
            dest.writeTypedArray(this.locks, flags);
            dest.writeTypedArray(this.openpowers, flags);
            dest.writeTypedArray(this.openpowersMemoHashList, flags);
            dest.writeTypedArray(this.lockFieldList, flags);
            dest.writeTypedArray(this.loopFieldList, flags);
            dest.writeTypedArray(this.openFieldList, flags);
        }

        protected LockInfo(Parcel in) {
            this.isNewLock = in.readByte() != 0;
            this.isPowerOff = in.readByte() != 0;
            this.powerOffMsg = in.readString();
            this.lockOk = in.readByte() != 0;
            this.loopOk = in.readByte() != 0;
            this.openOk = in.readByte() != 0;
            this.havesppower = in.readByte() != 0;
            this.reportOk = in.readByte() != 0;
            this.ycOk = in.readByte() != 0;
            this.postion = in.readString();
            this.recordid = in.readInt();
            this.recordtype = in.readString();
            this.objectname = in.readString();
            this.locks = in.createTypedArray(LocksBean.CREATOR);
            this.openpowers = in.createTypedArray(OpenPower.CREATOR);
            this.openpowersMemoHashList = in.createTypedArray(PowerMemo.CREATOR);
            this.lockFieldList = in.createTypedArray(FieldListBean.CREATOR);
            this.loopFieldList = in.createTypedArray(FieldListBean.CREATOR);
            this.openFieldList = in.createTypedArray(FieldListBean.CREATOR);
        }

        public static final Creator<LockInfo> CREATOR = new Creator<LockInfo>() {
            @Override
            public LockInfo createFromParcel(Parcel source) {
                return new LockInfo(source);
            }

            @Override
            public LockInfo[] newArray(int size) {
                return new LockInfo[size];
            }
        };
    }

    public static class FieldListBean implements Parcelable, Comparable {
        private int nfclock_companystepfields_nodeuuid;
        private int nfclock_companysteps_nodeuuid;
        private String datatype;
        private String showname;
        private boolean allstepshow;
        private String createtime;
        private String fixedField;
        /**
         * fieldValue : null
         * canChange : true
         */

        private String fieldValue;
        private boolean canChange;

        public int getNfclock_companystepfields_nodeuuid() {
            return nfclock_companystepfields_nodeuuid;
        }

        public void setNfclock_companystepfields_nodeuuid(int nfclock_companystepfields_nodeuuid) {
            this.nfclock_companystepfields_nodeuuid = nfclock_companystepfields_nodeuuid;
        }

        public int getNfclock_companysteps_nodeuuid() {
            return nfclock_companysteps_nodeuuid;
        }

        public void setNfclock_companysteps_nodeuuid(int nfclock_companysteps_nodeuuid) {
            this.nfclock_companysteps_nodeuuid = nfclock_companysteps_nodeuuid;
        }

        public String getDatatype() {
            return datatype;
        }

        public void setDatatype(String datatype) {
            this.datatype = datatype;
        }

        public String getShowname() {
            return showname;
        }

        public void setShowname(String showname) {
            this.showname = showname;
        }

        public boolean isAllstepshow() {
            return allstepshow;
        }

        public void setAllstepshow(boolean allstepshow) {
            this.allstepshow = allstepshow;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getFixedField() {
            return fixedField;
        }

        public void setFixedField(String fixedField) {
            this.fixedField = fixedField;
        }

        public String getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }

        public boolean isCanChange() {
            return canChange;
        }

        public void setCanChange(boolean canChange) {
            this.canChange = canChange;
        }

        public FieldListBean() {
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.nfclock_companystepfields_nodeuuid);
            dest.writeInt(this.nfclock_companysteps_nodeuuid);
            dest.writeString(this.datatype);
            dest.writeString(this.showname);
            dest.writeByte(this.allstepshow ? (byte) 1 : (byte) 0);
            dest.writeString(this.createtime);
            dest.writeString(this.fixedField);
            dest.writeString(this.fieldValue);
            dest.writeByte(this.canChange ? (byte) 1 : (byte) 0);
        }

        protected FieldListBean(Parcel in) {
            this.nfclock_companystepfields_nodeuuid = in.readInt();
            this.nfclock_companysteps_nodeuuid = in.readInt();
            this.datatype = in.readString();
            this.showname = in.readString();
            this.allstepshow = in.readByte() != 0;
            this.createtime = in.readString();
            this.fixedField = in.readString();
            this.fieldValue = in.readString();
            this.canChange = in.readByte() != 0;
        }

        public static final Creator<FieldListBean> CREATOR = new Creator<FieldListBean>() {
            @Override
            public FieldListBean createFromParcel(Parcel source) {
                return new FieldListBean(source);
            }

            @Override
            public FieldListBean[] newArray(int size) {
                return new FieldListBean[size];
            }
        };

        @Override
        public int compareTo(Object another) {
            if (another instanceof FieldListBean) {
                return ((FieldListBean) another).getDatatype().compareTo(datatype);
            }
            return 0;
        }
    }

    /**
     * 开锁权限信息对象
     */
    public static class OpenPower implements Comparable, Parcelable {
        private int level = 0;
        private String openpowername;
        private String openpowermemo;
        private int openpowerid;
        private String describe;
        private PowerMemo[] openpowersMemoHashList;

        public PowerMemo[] getOpenpowersMemoHashList() {
            return openpowersMemoHashList;
        }

        public void setOpenpowersMemoHashList(PowerMemo[] openpowersMemoHashList) {
            this.openpowersMemoHashList = openpowersMemoHashList;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getDescribe() {
            StringBuilder sb = new StringBuilder();
            if (openpowersMemoHashList != null) {
                for (PowerMemo powerMemo : openpowersMemoHashList) {
                    sb.append(powerMemo.toString());
                    sb.append("\n\n");
                }
                return sb.toString();
            }
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getOpenpowermemo() {
            return openpowermemo;
        }

        public void setOpenpowermemo(String openpowermemo) {
            this.openpowermemo = openpowermemo;
        }

        public String getOpenpowername() {
            return openpowername;
        }

        public void setOpenpowername(String openpowername) {
            this.openpowername = openpowername;
        }

        public int getOpenpowerid() {
            return openpowerid;
        }

        public void setOpenpowerid(int openpowerid) {
            this.openpowerid = openpowerid;
        }

        @Override
        public String toString() {
            return openpowername;
        }

        @Override
        public boolean equals(java.lang.Object o) {
            if (o instanceof OpenPower) {
                OpenPower openPower = (OpenPower) o;
                return openpowerid == openPower.openpowerid;
            }
            return super.equals(o);
        }

        @Override
        public int hashCode() {
            return (openpowerid + "").hashCode();
        }

        public String getInstance() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }


        @Override
        public int compareTo(java.lang.Object another) {
            if (another instanceof OpenPower) {
                OpenPower openPower = (OpenPower) another;
                if (openPower.level == level) {
                    return this.openpowername.compareToIgnoreCase(openPower.openpowername);
                } else {
                    return openPower.level - level;
                }
            }
            return 0;
        }


        public OpenPower() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.level);
            dest.writeString(this.openpowername);
            dest.writeString(this.openpowermemo);
            dest.writeInt(this.openpowerid);
            dest.writeString(this.describe);
            dest.writeTypedArray(this.openpowersMemoHashList, flags);
        }

        protected OpenPower(Parcel in) {
            this.level = in.readInt();
            this.openpowername = in.readString();
            this.openpowermemo = in.readString();
            this.openpowerid = in.readInt();
            this.describe = in.readString();
            this.openpowersMemoHashList = in.createTypedArray(PowerMemo.CREATOR);
        }

        public static final Creator<OpenPower> CREATOR = new Creator<OpenPower>() {
            @Override
            public OpenPower createFromParcel(Parcel source) {
                return new OpenPower(source);
            }

            @Override
            public OpenPower[] newArray(int size) {
                return new OpenPower[size];
            }
        };
    }

    /**
     * 权限信息描述对象
     */
    public static class PowerMemo implements Parcelable {
        private int key;
        private ErrMsg[] value;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (value == null) {
                return sb.toString();
            }
            for (ErrMsg errMsg : value) {
                sb.append(errMsg.key);
                sb.append("\n");
                for (String s : errMsg.errMsgList) {
                    sb.append(s);
                    sb.append("\n\n");
                }
            }
            return sb.toString();
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public ErrMsg[] getValue() {
            return value;
        }

        public void setValue(ErrMsg[] value) {
            this.value = value;
        }

        public static class ErrMsg implements Parcelable {
            private String key;
            private String[] errMsgList;

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                if (errMsgList == null) {
                    return sb.toString();
                }
                sb.append("\n");
                sb.append(key);
                sb.append("\n");
                for (String s : errMsgList) {
                    sb.append(s);
                    sb.append("\n");
                }
                return sb.toString();
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String[] getErrMsgList() {
                return errMsgList;
            }

            public void setErrMsgList(String[] errMsgList) {
                this.errMsgList = errMsgList;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.key);
                dest.writeStringArray(this.errMsgList);
            }

            public ErrMsg() {
            }

            protected ErrMsg(Parcel in) {
                this.key = in.readString();
                this.errMsgList = in.createStringArray();
            }

            public static final Creator<ErrMsg> CREATOR = new Creator<ErrMsg>() {
                @Override
                public ErrMsg createFromParcel(Parcel source) {
                    return new ErrMsg(source);
                }

                @Override
                public ErrMsg[] newArray(int size) {
                    return new ErrMsg[size];
                }
            };
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.key);
            dest.writeTypedArray(this.value, flags);
        }

        public PowerMemo() {
        }

        protected PowerMemo(Parcel in) {
            this.key = in.readInt();
            this.value = in.createTypedArray(ErrMsg.CREATOR);
        }

        public static final Creator<PowerMemo> CREATOR = new Creator<PowerMemo>() {
            @Override
            public PowerMemo createFromParcel(Parcel source) {
                return new PowerMemo(source);
            }

            @Override
            public PowerMemo[] newArray(int size) {
                return new PowerMemo[size];
            }
        };
    }

    /**
     * 角色对象
     */
    public static class Player implements Comparable {
        private String jsname;
        private String jsid;

        public String getJsname() {
            return jsname;
        }

        public void setJsname(String jsname) {
            this.jsname = jsname;
        }

        public String getJsid() {
            return jsid;
        }

        public void setJsid(String jsid) {
            this.jsid = jsid;
        }

        @Override
        public String toString() {
            return jsname;
        }

        public String getInstance() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }

        @Override
        public boolean equals(java.lang.Object o) {
            if (o instanceof Player) {
                Player player = (Player) o;
                return jsid.equals(player.jsid);
            }
            return super.equals(o);
        }

        @Override
        public int hashCode() {
            return jsid.hashCode();
        }

        @Override
        public int compareTo(java.lang.Object another) {
            if (another instanceof Player) {
                Player player = (Player) another;
                return jsid.compareToIgnoreCase(player.jsid);
            }
            return 0;
        }
    }

    /**
     * 权限子条目对象
     */
    public static class PowerItem implements Parcelable {
        private long id;
        private String itemname;
        private String itemtype;
        private String comop;
        private String createtime;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getItemname() {
            return itemname;
        }

        public void setItemname(String itemname) {
            this.itemname = itemname;
        }

        public String getItemtype() {
            return itemtype;
        }

        public void setItemtype(String itemtype) {
            this.itemtype = itemtype;
        }

        public String getComop() {
            return comop;
        }

        public void setComop(String comop) {
            this.comop = comop;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(this.id);
            dest.writeString(this.itemname);
            dest.writeString(this.itemtype);
            dest.writeString(this.comop);
            dest.writeString(this.createtime);
        }

        public PowerItem() {
        }

        protected PowerItem(Parcel in) {
            this.id = in.readLong();
            this.itemname = in.readString();
            this.itemtype = in.readString();
            this.comop = in.readString();
            this.createtime = in.readString();
        }

        public static final Parcelable.Creator<PowerItem> CREATOR = new Parcelable.Creator<PowerItem>() {
            @Override
            public PowerItem createFromParcel(Parcel source) {
                return new PowerItem(source);
            }

            @Override
            public PowerItem[] newArray(int size) {
                return new PowerItem[size];
            }
        };
    }

    /**
     * 运输记录对象
     */
    public static class Record implements Parcelable {

        /**
         * recordid : 5
         * openpowerid : -1
         * objectname : 沪G25355
         * companyid : 152
         * companyname : 上海电子锁管理公司
         * beginaddr : 上海市徐汇区宜山路靠近上海体检体检中心
         * begintime : /Date(1463207730000)/
         * endaddr :
         * endtime : /Date(1463207730000)/
         * errormsg :
         * reportpic :
         * isover : false
         * beginlat : 31.194566
         * beginlng : 121.427406
         * beginusername : dzsdzs
         * beginrealname : 测试人员
         * endlat :
         * endlng :
         * endusername :
         * endrealname :
         * transporttype : distribution
         */

        private int recordid;
        private String objectname;
        private int companyid;
        private String companyname;
        private String beginaddr;
        private String begintime;
        private String endaddr;
        private String endtime;
        private String errormsg;
        private String reportpic;
        private String reportmemo;
        private boolean isover;
        private String beginlat;
        private String beginlng;
        private String beginusername;
        private String beginrealname;
        private String endlat;
        private String endlng;
        private String endusername;
        private String endrealname;
        private String transporttype;
        /**
         * haveerrorover : false
         * lockok : false
         */

        private boolean haveerrorover;
        private boolean lockok;

        public int getRecordid() {
            return recordid;
        }

        public void setRecordid(int recordid) {
            this.recordid = recordid;
        }

        public String getReportmemo() {
            return reportmemo;
        }

        public void setReportmemo(String reportmemo) {
            this.reportmemo = reportmemo;
        }

        public String getObjectname() {
            return objectname;
        }

        public void setObjectname(String objectname) {
            this.objectname = objectname;
        }

        public int getCompanyid() {
            return companyid;
        }

        public void setCompanyid(int companyid) {
            this.companyid = companyid;
        }

        public String getCompanyname() {
            return companyname;
        }

        public void setCompanyname(String companyname) {
            this.companyname = companyname;
        }

        public String getBeginaddr() {
            return beginaddr;
        }

        public void setBeginaddr(String beginaddr) {
            this.beginaddr = beginaddr;
        }

        public String getBegintime() {
            return begintime;
        }

        public void setBegintime(String begintime) {
            this.begintime = begintime;
        }

        public String getEndaddr() {
            return endaddr;
        }

        public void setEndaddr(String endaddr) {
            this.endaddr = endaddr;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getErrormsg() {
            return errormsg;
        }

        public void setErrormsg(String errormsg) {
            this.errormsg = errormsg;
        }

        public String getReportpic() {
            return reportpic;
        }

        public void setReportpic(String reportpic) {
            this.reportpic = reportpic;
        }

        public boolean isIsover() {
            return isover;
        }

        public void setIsover(boolean isover) {
            this.isover = isover;
        }

        public String getBeginlat() {
            return beginlat;
        }

        public void setBeginlat(String beginlat) {
            this.beginlat = beginlat;
        }

        public String getBeginlng() {
            return beginlng;
        }

        public void setBeginlng(String beginlng) {
            this.beginlng = beginlng;
        }

        public String getBeginusername() {
            return beginusername;
        }

        public void setBeginusername(String beginusername) {
            this.beginusername = beginusername;
        }

        public String getBeginrealname() {
            return beginrealname;
        }

        public void setBeginrealname(String beginrealname) {
            this.beginrealname = beginrealname;
        }

        public String getEndlat() {
            return endlat;
        }

        public void setEndlat(String endlat) {
            this.endlat = endlat;
        }

        public String getEndlng() {
            return endlng;
        }

        public void setEndlng(String endlng) {
            this.endlng = endlng;
        }

        public String getEndusername() {
            return endusername;
        }

        public void setEndusername(String endusername) {
            this.endusername = endusername;
        }

        public String getEndrealname() {
            return endrealname;
        }

        public void setEndrealname(String endrealname) {
            this.endrealname = endrealname;
        }

        public String getTransporttype() {
            return transporttype;
        }

        public void setTransporttype(String transporttype) {
            this.transporttype = transporttype;
        }

        public Record() {
        }

        public boolean isHaveerrorover() {
            return haveerrorover;
        }

        public void setHaveerrorover(boolean haveerrorover) {
            this.haveerrorover = haveerrorover;
        }

        public boolean isLockok() {
            return lockok;
        }

        public void setLockok(boolean lockok) {
            this.lockok = lockok;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.recordid);
            dest.writeString(this.objectname);
            dest.writeInt(this.companyid);
            dest.writeString(this.companyname);
            dest.writeString(this.beginaddr);
            dest.writeString(this.begintime);
            dest.writeString(this.endaddr);
            dest.writeString(this.endtime);
            dest.writeString(this.errormsg);
            dest.writeString(this.reportpic);
            dest.writeString(this.reportmemo);
            dest.writeByte(this.isover ? (byte) 1 : (byte) 0);
            dest.writeString(this.beginlat);
            dest.writeString(this.beginlng);
            dest.writeString(this.beginusername);
            dest.writeString(this.beginrealname);
            dest.writeString(this.endlat);
            dest.writeString(this.endlng);
            dest.writeString(this.endusername);
            dest.writeString(this.endrealname);
            dest.writeString(this.transporttype);
            dest.writeByte(this.haveerrorover ? (byte) 1 : (byte) 0);
            dest.writeByte(this.lockok ? (byte) 1 : (byte) 0);
        }

        protected Record(Parcel in) {
            this.recordid = in.readInt();
            this.objectname = in.readString();
            this.companyid = in.readInt();
            this.companyname = in.readString();
            this.beginaddr = in.readString();
            this.begintime = in.readString();
            this.endaddr = in.readString();
            this.endtime = in.readString();
            this.errormsg = in.readString();
            this.reportpic = in.readString();
            this.reportmemo = in.readString();
            this.isover = in.readByte() != 0;
            this.beginlat = in.readString();
            this.beginlng = in.readString();
            this.beginusername = in.readString();
            this.beginrealname = in.readString();
            this.endlat = in.readString();
            this.endlng = in.readString();
            this.endusername = in.readString();
            this.endrealname = in.readString();
            this.transporttype = in.readString();
            this.haveerrorover = in.readByte() != 0;
            this.lockok = in.readByte() != 0;
        }

        public static final Creator<Record> CREATOR = new Creator<Record>() {
            @Override
            public Record createFromParcel(Parcel source) {
                return new Record(source);
            }

            @Override
            public Record[] newArray(int size) {
                return new Record[size];
            }
        };
    }

    public static class OpenRecord {

        @Override
        public String toString() {
            return "OpenRecord{" +
                    "id=" + id +
                    ", lockrfid='" + lockrfid + '\'' +
                    ", createtime='" + createtime + '\'' +
                    ", errormsg='" + errormsg + '\'' +
                    ", openok=" + openok +
                    ", openusername='" + openusername + '\'' +
                    ", recordid=" + recordid +
                    ", openaddr='" + openaddr + '\'' +
                    ", openpic='" + openpic + '\'' +
                    ", repeatopen='" + repeatopen + '\'' +
                    '}';
        }

        /**
         * id : 34
         * lockrfid : 536D6E6C00C680
         * createtime : /Date(1463816359847)/
         * errormsg : null
         * openok : true
         * openusername : lgfdzs(lgf)
         * recordid : 32
         * openaddr : 上海市徐汇区宜山路靠近上海体检体检中心
         * openpic : /uploadfile/default/tpwsc.png
         */

        private int id;
        private String lockrfid;
        private String createtime;
        private String errormsg;
        private boolean openok;
        private String openusername;
        private int recordid;
        private String openaddr;
        private String openpic;
        /**
         * repeatopen : N
         */

        private String repeatopen;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLockrfid() {
            return lockrfid;
        }

        public void setLockrfid(String lockrfid) {
            this.lockrfid = lockrfid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getErrormsg() {
            Gson gson = new Gson();
            try {
                PowerMemo.ErrMsg[] errMsgs = gson.fromJson(errormsg, PowerMemo.ErrMsg[].class);
                errormsg = "";
                for (PowerMemo.ErrMsg errMsg : errMsgs) {
                    errormsg += errMsg.toString();
                }
            } catch (Exception e) {

            }
            return errormsg;
        }

        public void setErrormsg(String errormsg) {
            this.errormsg = errormsg;
        }

        public boolean isOpenok() {
            return openok;
        }

        public void setOpenok(boolean openok) {
            this.openok = openok;
        }

        public String getOpenusername() {
            return openusername;
        }

        public void setOpenusername(String openusername) {
            this.openusername = openusername;
        }

        public int getRecordid() {
            return recordid;
        }

        public void setRecordid(int recordid) {
            this.recordid = recordid;
        }

        public String getOpenaddr() {
            return openaddr;
        }

        public void setOpenaddr(String openaddr) {
            this.openaddr = openaddr;
        }

        public String getOpenpic() {
            return openpic;
        }

        public void setOpenpic(String openpic) {
            this.openpic = openpic;
        }

        public String getRepeatopen() {
            return repeatopen;
        }

        public void setRepeatopen(String repeatopen) {
            this.repeatopen = repeatopen;
        }
    }

    /**
     * 服务端返回对象
     */
    public static class ReturnObject {
        private boolean bOK = false;
        private String sMsg = "";
        private java.lang.Object m_ReturnOBJ = null;

        public boolean isbOK() {
            return bOK;
        }

        public void setbOK(boolean bOK) {
            this.bOK = bOK;
        }

        public String getsMsg() {
            return sMsg;
        }

        public void setsMsg(String sMsg) {
            this.sMsg = sMsg;
        }

        public java.lang.Object getM_ReturnOBJ() {
            return m_ReturnOBJ;
        }

        public void setM_ReturnOBJ(java.lang.Object m_ReturnOBJ) {
            this.m_ReturnOBJ = m_ReturnOBJ;
        }

        public JsonObject getM_ReturnOBJJsonObject() {
            Gson gson = new Gson();
            JsonObject jso = gson.fromJson(gson.toJson(this.getM_ReturnOBJ()), JsonObject.class);
            return jso;
        }

        public JsonArray getM_ReturnOBJJsonArray() {
            Gson gson = new Gson();
            JsonArray jsa = gson.fromJson(gson.toJson(this.getM_ReturnOBJ()), JsonArray.class);
            return jsa;
        }
    }

    /**
     * 服务端返回数组对象
     */
    public static class ReturnData {
        private boolean bOK;
        private int total;
        private JsonArray rows;

        public boolean isbOK() {
            return bOK;
        }

        public void setbOK(boolean bOK) {
            this.bOK = bOK;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public JsonArray getRows() {
            return rows;
        }

        public void setRows(JsonArray rows) {
            this.rows = rows;
        }
    }

    /**
     * 用户信息
     */
    public static class User implements Comparable {
        private long id;
        private String username;
        private String realname;
        private long companyid;

        @Override
        public String toString() {
            return username + "(" + realname + ")";
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public long getCompanyid() {
            return companyid;
        }

        public void setCompanyid(long companyid) {
            this.companyid = companyid;
        }

        @Override
        public int compareTo(java.lang.Object another) {
            if (another instanceof User) {
                User user = (User) another;
                return username.compareToIgnoreCase(user.username);
            }
            return 0;
        }
    }

    /**
     * 手持端用户操作信息
     */
    public static class UserInfo implements Parcelable {

        /**
         * userid : 874
         * orgna_id : 359
         * jsid : 017101010
         * companyid : 152
         */

        private MessageBean message;
        /**
         * LGKey : 59a6f273d9294128b1b7a633a96da70f
         * realname : 测试人员
         * canuse : Y
         * powername : 电子锁企业管理员
         * username : dzsdzs
         * company : 上海电子锁管理公司
         * overtime : 2099/1/1 8:00:00
         * addusername : lyq
         * orgna_name : 总部
         */

        private MUserInfoBean m_UserInfo;

        /**
         * addNewLock : true
         * addNewPowers : true
         * choicePower : true
         * getAllPwd : true
         * loop : true
         */
        private UserPowerBean userPowerBean;

        /**
         * message : {"userid":"874","orgna_id":"359","jsid":"017101010","companyid":"152"}
         * m_UserInfo : {"LGKey":"59a6f273d9294128b1b7a633a96da70f","realname":"测试人员","canuse":"Y","powername":"电子锁企业管理员","username":"dzsdzs","company":"上海电子锁管理公司","overtime":"2099/1/1 8:00:00","addusername":"lyq","orgna_name":"总部"}
         * bOK : true
         */


        private boolean bOK;

        public MessageBean getMessage() {
            return message;
        }

        public void setMessage(MessageBean message) {
            this.message = message;
        }

        public MUserInfoBean getM_UserInfo() {
            return m_UserInfo;
        }

        public void setM_UserInfo(MUserInfoBean m_UserInfo) {
            this.m_UserInfo = m_UserInfo;
        }

        public UserPowerBean getUserPowerBean() {
            return userPowerBean;
        }

        public void setUserPowerBean(UserPowerBean userPowerBean) {
            this.userPowerBean = userPowerBean;
        }

        public boolean isBOK() {
            return bOK;
        }

        public void setBOK(boolean bOK) {
            this.bOK = bOK;
        }

        public static class MessageBean implements Parcelable {
            private String userid;
            private String orgna_id;
            private String jsid;
            private int companyid;

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getOrgna_id() {
                return orgna_id;
            }

            public void setOrgna_id(String orgna_id) {
                this.orgna_id = orgna_id;
            }

            public String getJsid() {
                return jsid;
            }

            public void setJsid(String jsid) {
                this.jsid = jsid;
            }

            public int getCompanyid() {
                return companyid;
            }

            public void setCompanyid(int companyid) {
                this.companyid = companyid;
            }

            public MessageBean() {
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.userid);
                dest.writeString(this.orgna_id);
                dest.writeString(this.jsid);
                dest.writeInt(this.companyid);
            }

            protected MessageBean(Parcel in) {
                this.userid = in.readString();
                this.orgna_id = in.readString();
                this.jsid = in.readString();
                this.companyid = in.readInt();
            }

            public static final Creator<MessageBean> CREATOR = new Creator<MessageBean>() {
                @Override
                public MessageBean createFromParcel(Parcel source) {
                    return new MessageBean(source);
                }

                @Override
                public MessageBean[] newArray(int size) {
                    return new MessageBean[size];
                }
            };
        }

        public static class MUserInfoBean implements Parcelable {
            private String LGKey;
            private String realname;
            private String canuse;
            private String powername;
            private String username;
            private String company;
            private String overtime;
            private String addusername;
            private String orgna_name;
            private String password;

            public String getLGKey() {
                return LGKey;
            }

            public void setLGKey(String LGKey) {
                this.LGKey = LGKey;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getCanuse() {
                return canuse;
            }

            public void setCanuse(String canuse) {
                this.canuse = canuse;
            }

            public String getPowername() {
                return powername;
            }

            public void setPowername(String powername) {
                this.powername = powername;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getOvertime() {
                return overtime;
            }

            public void setOvertime(String overtime) {
                this.overtime = overtime;
            }

            public String getAddusername() {
                return addusername;
            }

            public void setAddusername(String addusername) {
                this.addusername = addusername;
            }

            public String getOrgna_name() {
                return orgna_name;
            }

            public void setOrgna_name(String orgna_name) {
                this.orgna_name = orgna_name;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.LGKey);
                dest.writeString(this.realname);
                dest.writeString(this.canuse);
                dest.writeString(this.powername);
                dest.writeString(this.username);
                dest.writeString(this.company);
                dest.writeString(this.overtime);
                dest.writeString(this.addusername);
                dest.writeString(this.orgna_name);
                dest.writeString(this.password);
            }

            public MUserInfoBean() {
            }

            protected MUserInfoBean(Parcel in) {
                this.LGKey = in.readString();
                this.realname = in.readString();
                this.canuse = in.readString();
                this.powername = in.readString();
                this.username = in.readString();
                this.company = in.readString();
                this.overtime = in.readString();
                this.addusername = in.readString();
                this.orgna_name = in.readString();
                this.password = in.readString();
            }

            public static final Creator<MUserInfoBean> CREATOR = new Creator<MUserInfoBean>() {
                @Override
                public MUserInfoBean createFromParcel(Parcel source) {
                    return new MUserInfoBean(source);
                }

                @Override
                public MUserInfoBean[] newArray(int size) {
                    return new MUserInfoBean[size];
                }
            };
        }

        public static class UserPowerBean implements Parcelable {
            private boolean addNewLock;
            private boolean addNewPowers;
            private boolean choicePower;
            private boolean createNewRecord;
            private boolean getAllPwd;
            private boolean restLock;
            private boolean loop;

            public boolean isCreateNewRecord() {
                return createNewRecord;
            }

            public void setCreateNewRecord(boolean createNewRecord) {
                this.createNewRecord = createNewRecord;
            }

            /**
             * YCOvers : true
             */


            private boolean YCOvers;

            public boolean isRestLock() {
                return restLock;
            }

            public void setRestLock(boolean restLock) {
                this.restLock = restLock;
            }

            public boolean isAddNewLock() {
                return addNewLock;
            }

            public void setAddNewLock(boolean addNewLock) {
                this.addNewLock = addNewLock;
            }

            public boolean isAddNewPowers() {
                return addNewPowers;
            }

            public void setAddNewPowers(boolean addNewPowers) {
                this.addNewPowers = addNewPowers;
            }

            public boolean isChoicePower() {
                return choicePower;
            }

            public void setChoicePower(boolean choicePower) {
                this.choicePower = choicePower;
            }

            public boolean isGetAllPwd() {
                return getAllPwd;
            }

            public void setGetAllPwd(boolean getAllPwd) {
                this.getAllPwd = getAllPwd;
            }

            public boolean isLoop() {
                return loop;
            }

            public void setLoop(boolean loop) {
                this.loop = loop;
            }

            public UserPowerBean() {
            }

            public boolean isYCOvers() {
                return YCOvers;
            }

            public void setYCOvers(boolean YCOvers) {
                this.YCOvers = YCOvers;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeByte(this.addNewLock ? (byte) 1 : (byte) 0);
                dest.writeByte(this.addNewPowers ? (byte) 1 : (byte) 0);
                dest.writeByte(this.choicePower ? (byte) 1 : (byte) 0);
                dest.writeByte(this.createNewRecord ? (byte) 1 : (byte) 0);
                dest.writeByte(this.getAllPwd ? (byte) 1 : (byte) 0);
                dest.writeByte(this.restLock ? (byte) 1 : (byte) 0);
                dest.writeByte(this.loop ? (byte) 1 : (byte) 0);
                dest.writeByte(this.YCOvers ? (byte) 1 : (byte) 0);
            }

            protected UserPowerBean(Parcel in) {
                this.addNewLock = in.readByte() != 0;
                this.addNewPowers = in.readByte() != 0;
                this.choicePower = in.readByte() != 0;
                this.createNewRecord = in.readByte() != 0;
                this.getAllPwd = in.readByte() != 0;
                this.restLock = in.readByte() != 0;
                this.loop = in.readByte() != 0;
                this.YCOvers = in.readByte() != 0;
            }

            public static final Creator<UserPowerBean> CREATOR = new Creator<UserPowerBean>() {
                @Override
                public UserPowerBean createFromParcel(Parcel source) {
                    return new UserPowerBean(source);
                }

                @Override
                public UserPowerBean[] newArray(int size) {
                    return new UserPowerBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.message, flags);
            dest.writeParcelable(this.m_UserInfo, flags);
            dest.writeParcelable(this.userPowerBean, flags);
            dest.writeByte(this.bOK ? (byte) 1 : (byte) 0);
        }

        public UserInfo() {
        }

        protected UserInfo(Parcel in) {
            this.message = in.readParcelable(MessageBean.class.getClassLoader());
            this.m_UserInfo = in.readParcelable(MUserInfoBean.class.getClassLoader());
            this.userPowerBean = in.readParcelable(UserPowerBean.class.getClassLoader());
            this.bOK = in.readByte() != 0;
        }

        public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
            @Override
            public UserInfo createFromParcel(Parcel source) {
                return new UserInfo(source);
            }

            @Override
            public UserInfo[] newArray(int size) {
                return new UserInfo[size];
            }
        };
    }

    /**
     * 部门对象
     */
    public static class Organ {
        private String orgna_id;
        private String parent_id;
        private String orgna_name;
        private String orgmemo;
        private String companyid;
        private String addr;
        private int level;
        private LinkedTreeMap<String, Organ> m_ChildHash;

        public void init(ArrayList<Organ> organs, int level) {
            this.level = level;
            if (!TextUtils.isEmpty(orgna_id)) {
                organs.add(this);
            }
            if (m_ChildHash != null) {
                for (Map.Entry<String, Organ> e : m_ChildHash.entrySet()) {
                    Organ o = e.getValue();
                    o.init(organs, level + 1);
                }
            }
        }

        @Override
        public int hashCode() {
            return orgna_id.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Organ) {
                return ((Organ) o).orgna_id.equals(orgna_id);
            }
            return super.equals(o);
        }

        @Override
        public String toString() {
            return orgna_name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getOrgna_id() {
            return orgna_id;
        }

        public void setOrgna_id(String orgna_id) {
            this.orgna_id = orgna_id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getOrgna_name() {
            return orgna_name;
        }

        public void setOrgna_name(String orgna_name) {
            this.orgna_name = orgna_name;
        }

        public String getOrgmemo() {
            return orgmemo;
        }

        public void setOrgmemo(String orgmemo) {
            this.orgmemo = orgmemo;
        }

        public String getCompanyid() {
            return companyid;
        }

        public void setCompanyid(String companyid) {
            this.companyid = companyid;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public LinkedTreeMap<String, Organ> getM_ChildHash() {
            return m_ChildHash;
        }

        public void setM_ChildHash(LinkedTreeMap<String, Organ> m_ChildHash) {
            this.m_ChildHash = m_ChildHash;
        }
    }

    /**
     * 地图显示对象
     */
    public static class MapPoint implements Serializable {
        private String time;
        private String addr;
        private String lockobject;
        private String status;
        private String acount;
        private double lng;
        private double lat;
        private String lockrfid;
        private String msg;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getLockobject() {
            return lockobject;
        }

        public void setLockobject(String lockobject) {
            this.lockobject = lockobject;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAcount() {
            return acount;
        }

        public void setAcount(String acount) {
            this.acount = acount;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public String getLockrfid() {
            return lockrfid;
        }

        public void setLockrfid(String lockrfid) {
            this.lockrfid = lockrfid;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

    }

    public static class AllMapPoints implements Serializable {
        private MapPoint[][] mapData;

        public MapPoint[][] getMapData() {
            return mapData;
        }

        public void setMapData(MapPoint[][] mapData) {
            this.mapData = mapData;
        }

    }

    public static class StepInfo implements Parcelable {


        /**
         * optok : lock
         * optusername : lgfdzs
         * nfclock_objectpostionname : 下方
         * pic :
         * addr : 上海市徐汇区宜山路靠近上海体检体检中心
         * logitude : 121.427415
         * nfclock_usepostionrecord_nodeuuid : 55
         * latitude : 31.194577
         * spfieldjson : []
         * postionlink : 9d241fe332c24677ae7e0aef58a3ae3f
         * recordid : 21
         * createtime : /Date(1463801868000)/
         * stepname : 上锁
         * stepindex : 0
         * callbackok : false
         * errormsg :
         * optrealname : lgf
         */

        private String optok;
        private String optusername;
        private String nfclock_objectpostionname;
        private String pic;
        private String addr;
        private String logitude;
        private int nfclock_usepostionrecord_nodeuuid;
        private String latitude;
        private String spfieldjson;
        private String postionlink;
        private int recordid;
        private String createtime;
        private String stepname;
        private int stepindex;
        private boolean callbackok;
        private String errormsg;
        private String optrealname;
        private FieldListBean[] fieldListBeen;

        public FieldListBean[] getFieldListBeen() {
            return fieldListBeen;
        }

        public void setFieldListBeen(FieldListBean[] fieldListBeen) {
            this.fieldListBeen = fieldListBeen;
        }

        public String getOptok() {
            return optok;
        }

        public void setOptok(String optok) {
            this.optok = optok;
        }

        public String getOptusername() {
            return optusername;
        }

        public void setOptusername(String optusername) {
            this.optusername = optusername;
        }

        public String getNfclock_objectpostionname() {
            return nfclock_objectpostionname;
        }

        public void setNfclock_objectpostionname(String nfclock_objectpostionname) {
            this.nfclock_objectpostionname = nfclock_objectpostionname;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getLogitude() {
            return logitude;
        }

        public void setLogitude(String logitude) {
            this.logitude = logitude;
        }

        public int getNfclock_usepostionrecord_nodeuuid() {
            return nfclock_usepostionrecord_nodeuuid;
        }

        public void setNfclock_usepostionrecord_nodeuuid(int nfclock_usepostionrecord_nodeuuid) {
            this.nfclock_usepostionrecord_nodeuuid = nfclock_usepostionrecord_nodeuuid;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getSpfieldjson() {
            return spfieldjson;
        }

        public void setSpfieldjson(String spfieldjson) {
            this.spfieldjson = spfieldjson;
        }

        public String getPostionlink() {
            return postionlink;
        }

        public void setPostionlink(String postionlink) {
            this.postionlink = postionlink;
        }

        public int getRecordid() {
            return recordid;
        }

        public void setRecordid(int recordid) {
            this.recordid = recordid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getStepname() {
            return stepname;
        }

        public void setStepname(String stepname) {
            this.stepname = stepname;
        }

        public int getStepindex() {
            return stepindex;
        }

        public void setStepindex(int stepindex) {
            this.stepindex = stepindex;
        }

        public boolean isCallbackok() {
            return callbackok;
        }

        public void setCallbackok(boolean callbackok) {
            this.callbackok = callbackok;
        }

        public String getErrormsg() {
            Gson gson = new Gson();
            try {
                JSONModel.PowerMemo.ErrMsg[] errMsgs = gson.fromJson(errormsg, JSONModel.PowerMemo.ErrMsg[].class);
                errormsg = "";
                for (JSONModel.PowerMemo.ErrMsg errMsg : errMsgs) {
                    errormsg += errMsg.toString();
                }
            } catch (Exception e) {

            }
            return errormsg;
        }

        public void setErrormsg(String errormsg) {
            this.errormsg = errormsg;
        }

        public String getOptrealname() {
            return optrealname;
        }

        public void setOptrealname(String optrealname) {
            this.optrealname = optrealname;
        }

        public StepInfo() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.optok);
            dest.writeString(this.optusername);
            dest.writeString(this.nfclock_objectpostionname);
            dest.writeString(this.pic);
            dest.writeString(this.addr);
            dest.writeString(this.logitude);
            dest.writeInt(this.nfclock_usepostionrecord_nodeuuid);
            dest.writeString(this.latitude);
            dest.writeString(this.spfieldjson);
            dest.writeString(this.postionlink);
            dest.writeInt(this.recordid);
            dest.writeString(this.createtime);
            dest.writeString(this.stepname);
            dest.writeInt(this.stepindex);
            dest.writeByte(this.callbackok ? (byte) 1 : (byte) 0);
            dest.writeString(this.errormsg);
            dest.writeString(this.optrealname);
            dest.writeTypedArray(this.fieldListBeen, flags);
        }

        protected StepInfo(Parcel in) {
            this.optok = in.readString();
            this.optusername = in.readString();
            this.nfclock_objectpostionname = in.readString();
            this.pic = in.readString();
            this.addr = in.readString();
            this.logitude = in.readString();
            this.nfclock_usepostionrecord_nodeuuid = in.readInt();
            this.latitude = in.readString();
            this.spfieldjson = in.readString();
            this.postionlink = in.readString();
            this.recordid = in.readInt();
            this.createtime = in.readString();
            this.stepname = in.readString();
            this.stepindex = in.readInt();
            this.callbackok = in.readByte() != 0;
            this.errormsg = in.readString();
            this.optrealname = in.readString();
            this.fieldListBeen = in.createTypedArray(FieldListBean.CREATOR);
        }

        public static final Creator<StepInfo> CREATOR = new Creator<StepInfo>() {
            @Override
            public StepInfo createFromParcel(Parcel source) {
                return new StepInfo(source);
            }

            @Override
            public StepInfo[] newArray(int size) {
                return new StepInfo[size];
            }
        };
    }

    public static class ErrorInfo implements Parcelable {

        /**
         * errormsg :
         * errortime : /Date(1464082268830)/
         * bok : false
         * errorpic : /uploadfile/NFCLock/e7caf0f0348c423a8a87847a025d6153.jpg
         */

        private String errormsg;
        private String errortime;
        private boolean bok;
        private String errorpic;
        /**
         * errorusername : dzs
         * errorlng : 121.427529
         * erroraddr : 上海市徐汇区宜山北路靠近上海体检体检中心
         * errorrealname : lgf
         * errorlat : 31.19471
         */

        private String errorusername;
        private String errorlng;
        private String erroraddr;
        private String errorrealname;
        private String errorlat;

        public String getErrormsg() {
            return errormsg;
        }

        public void setErrormsg(String errormsg) {
            this.errormsg = errormsg;
        }

        public String getErrortime() {
            return errortime;
        }

        public void setErrortime(String errortime) {
            this.errortime = errortime;
        }

        public boolean isBok() {
            return bok;
        }

        public void setBok(boolean bok) {
            this.bok = bok;
        }

        public String getErrorpic() {
            return errorpic;
        }

        public void setErrorpic(String errorpic) {
            this.errorpic = errorpic;
        }

        public ErrorInfo() {
        }

        public String getErrorusername() {
            return errorusername;
        }

        public void setErrorusername(String errorusername) {
            this.errorusername = errorusername;
        }

        public String getErrorlng() {
            return errorlng;
        }

        public void setErrorlng(String errorlng) {
            this.errorlng = errorlng;
        }

        public String getErroraddr() {
            return erroraddr;
        }

        public void setErroraddr(String erroraddr) {
            this.erroraddr = erroraddr;
        }

        public String getErrorrealname() {
            return errorrealname;
        }

        public void setErrorrealname(String errorrealname) {
            this.errorrealname = errorrealname;
        }

        public String getErrorlat() {
            return errorlat;
        }

        public void setErrorlat(String errorlat) {
            this.errorlat = errorlat;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.errormsg);
            dest.writeString(this.errortime);
            dest.writeByte(this.bok ? (byte) 1 : (byte) 0);
            dest.writeString(this.errorpic);
            dest.writeString(this.errorusername);
            dest.writeString(this.errorlng);
            dest.writeString(this.erroraddr);
            dest.writeString(this.errorrealname);
            dest.writeString(this.errorlat);
        }

        protected ErrorInfo(Parcel in) {
            this.errormsg = in.readString();
            this.errortime = in.readString();
            this.bok = in.readByte() != 0;
            this.errorpic = in.readString();
            this.errorusername = in.readString();
            this.errorlng = in.readString();
            this.erroraddr = in.readString();
            this.errorrealname = in.readString();
            this.errorlat = in.readString();
        }

        public static final Creator<ErrorInfo> CREATOR = new Creator<ErrorInfo>() {
            @Override
            public ErrorInfo createFromParcel(Parcel source) {
                return new ErrorInfo(source);
            }

            @Override
            public ErrorInfo[] newArray(int size) {
                return new ErrorInfo[size];
            }
        };
    }

}
