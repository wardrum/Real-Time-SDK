package com.refinitiv.eta.json.converter;

/**
 * An instance of {@link ParseJsonOptions} class holds parameters
 * that are passed to {@link JsonConverter#parseJsonBuffer} method
 */
public interface ParseJsonOptions {

    /**
     * Getter for converter flags
     * @return ParseJsonOptions flags
     */
    int getConverterFlags();

    /**
     * Setter for converter flags
     * @param converterFlags converter flags value to be set
     */
    void setConverterFlags(int converterFlags);

    /**
     * Getter for protocol type
     * @return the type of the protocol of the current JSON message
     */
    int getProtocolType();

    /**
     * Setter for json protocol type
     * @param protocolType the value of the json protocol type to be set
     */
    void setProtocolType(int protocolType);

    /**
     * Resets the default values of the fields of the current {@link ParseJsonOptions} instance
     */
    void clear();
}
