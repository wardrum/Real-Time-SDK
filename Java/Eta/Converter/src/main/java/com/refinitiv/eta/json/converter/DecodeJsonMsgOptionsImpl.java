package com.refinitiv.eta.json.converter;

class DecodeJsonMsgOptionsImpl implements DecodeJsonMsgOptions {

    private int converterFlags;
    private int jsonProtocolType;
    private int rsslProtocolType;
    private int majorVersion;
    private int minorVersion;

    DecodeJsonMsgOptionsImpl() {}

    public int getRsslProtocolType() {
        return rsslProtocolType;
    }

    public void setRsslProtocolType(int rsslProtocolType) {
        this.rsslProtocolType = rsslProtocolType;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getJsonProtocolType() {
        return jsonProtocolType;
    }

    public void setJsonProtocolType(int jsonProtocolType) {
        this.jsonProtocolType = jsonProtocolType;
    }

    public int getConverterFlags() {
        return converterFlags;
    }

    public void setConverterFlags(int converterFlags) {
        this.converterFlags = converterFlags;
    }

    @Override
    public void clear() {
        converterFlags = 0;
        jsonProtocolType = JsonProtocol.JSON_JPT_UNKNOWN;
        rsslProtocolType = 0;
        majorVersion = 0;
        minorVersion = 0;
    }
}
