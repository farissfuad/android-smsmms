/*
 * Copyright 2013 Jacob Klinker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.klinker.android.send_message;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Class to hold all relevant message information to send
 * @author Jake Klinker
 */
public class Message {

    private String text;
    private String[] addresses;
    private Bitmap[] images;

    /**
     * Default constructor
     */
    public Message() {
        this("", new String[] {""});
    }

    /**
     * Constructor
     * @param text is the message to send
     * @param address is the phone number to send to
     */
    public Message(String text, String address) {
        this(text, address.trim().split(" "));
    }

    /**
     * Constructor
     * @param text is the message to send
     * @param addresses is an array of phone numbers to send to
     */
    public Message(String text, String[] addresses) {
        this.text = text;
        this.addresses = addresses;
        this.images = new Bitmap[0];
    }

    /**
     * Constructor
     * @param text is the message to send
     * @param address is the phone number to send to
     * @param image is the image that you want to send
     */
    public Message(String text, String address, Bitmap image) {
        this(text, address.trim().split(" "), new Bitmap[] {image});
    }

    /**
     * Constructor
     * @param text is the message to send
     * @param addresses is an array of phone numbers to send to
     * @param image is the image that you want to send
     */
    public Message(String text, String[] addresses, Bitmap image) {
        this(text, addresses, new Bitmap[] {image});
    }

    /**
     * Constructor
     * @param text is the message to send
     * @param address is the phone number to send to
     * @param images is an array of images that you want to send
     */
    public Message(String text, String address, Bitmap[] images) {
        this(text, address.trim().split(" "), images);
    }

    /**
     * Constructor
     * @param text is the message to send
     * @param addresses is an array of phone numbers to send to
     * @param images is an array of images that you want to send
     */
    public Message(String text, String[] addresses, Bitmap[] images) {
        this.text = text;
        this.addresses = addresses;
        this.images = images;
    }

    /**
     * Sets the message
     * @param text is the string to set message to
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Sets recipients
     * @param addresses is the array of recipients to send to
     */
    public void setAddresses(String[] addresses) {
        this.addresses = addresses;
    }

    /**
     * Sets single recipient
     * @param address is the phone number of the recipient
     */
    public void setAddress(String address) {
        this.addresses = new String[1];
        this.addresses[0] = address;
    }

    /**
     * Sets images
     * @param images is the array of images to send to recipient
     */
    public void setImages(Bitmap[] images) {
        this.images = images;
    }

    /**
     * Sets image
     * @param image is the single image to send to recipient
     */
    public void setImage(Bitmap image) {
        this.images = new Bitmap[1];
        this.images[0] = image;
    }

    /**
     * Method to add another recipient to the object
     * @param address is the string of the recipients phone number to add to end of recipients array
     */
    public void addAddress(String address) {
        String[] temp = this.addresses;

        if (temp == null) {
            temp = new String[0];
        }

        this.addresses = new String[temp.length + 1];

        for (int i = 0; i < temp.length; i++) {
            this.addresses[i] = temp[i];
        }

        this.addresses[temp.length] = address;
    }

    /**
     * Add another image to the object
     * @param image is the image that you want to add to the end of the bitmaps array
     */
    public void addImage(Bitmap image) {
        Bitmap[] temp = this.images;

        if (temp == null) {
            temp = new Bitmap[0];
        }

        this.images = new Bitmap[temp.length + 1];

        for (int i = 0; i < temp.length; i++) {
            this.images[i] = temp[i];
        }

        this.images[temp.length] = image;
    }

    /**
     * Gets the text of the message to send
     * @return the string of the message to send
     */
    public String getText() {
        return this.text;
    }

    /**
     * Gets the addresses of the message
     * @return an array of strings with all of the addresses
     */
    public String[] getAddresses() {
        return this.addresses;
    }

    /**
     * Gets the images in the message
     * @return an array of bitmaps with all of the images
     */
    public Bitmap[] getImages() {
        return this.images;
    }

    /**
     * Static method to convert a bitmap into a byte array to easily send it over http
     * @param image is the image to convert
     * @return a byte array of the image data
     */
    public static byte[] bitmapToByteArray(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
