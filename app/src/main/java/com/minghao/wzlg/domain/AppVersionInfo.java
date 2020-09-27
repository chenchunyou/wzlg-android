package com.minghao.wzlg.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * APP版本信息对象 app_version_info
 * 
 * @author chunyou
 * @date 2020-09-01
 */
public class AppVersionInfo
{
    /** 自增主键id */
    private int versionId;

    /** 应用包名 */

    private String appPackage;

    /** 版本号 */
    private int versionCode;

    /** 版本名称 */
    private String versionName;

    /** 更新内容 */
    private String updateContent;

    /** 强制更新标识（0-非强制，1-强制） */
    private int updateRequired;

    /** 文件大小 */
    private int fileSize;

    /** 文件下载地址 */
    private String downloadUrl;

    public void setVersionId(int versionId)
    {
        this.versionId = versionId;
    }

    public int getVersionId()
    {
        return versionId;
    }
    public void setAppPackage(String appPackage) 
    {
        this.appPackage = appPackage;
    }

    public String getAppPackage() 
    {
        return appPackage;
    }
    public void setVersionCode(int versionCode)
    {
        this.versionCode = versionCode;
    }

    public int getVersionCode()
    {
        return versionCode;
    }
    public void setVersionName(String versionName) 
    {
        this.versionName = versionName;
    }

    public String getVersionName() 
    {
        return versionName;
    }
    public void setUpdateContent(String updateContent) 
    {
        this.updateContent = updateContent;
    }

    public String getUpdateContent() 
    {
        return updateContent;
    }
    public void setUpdateRequired(int updateRequired)
    {
        this.updateRequired = updateRequired;
    }

    public int getUpdateRequired()
    {
        return updateRequired;
    }
    public void setFileSize(int fileSize)
    {
        this.fileSize = fileSize;
    }

    public int getFileSize()
    {
        return fileSize;
    }
    public void setDownloadUrl(String downloadUrl) 
    {
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadUrl() 
    {
        return downloadUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("versionId", getVersionId())
            .append("appPackage", getAppPackage())
            .append("versionCode", getVersionCode())
            .append("versionName", getVersionName())
            .append("updateContent", getUpdateContent())
            .append("updateRequired", getUpdateRequired())
            .append("fileSize", getFileSize())
            .append("downloadUrl", getDownloadUrl())
            .toString();
    }
}
