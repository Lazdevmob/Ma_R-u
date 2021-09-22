package com.ocr.laz.mareu.di;

import com.ocr.laz.mareu.service.ImplMeetingApiService;
import com.ocr.laz.mareu.service.MeetingApiService;

 /**
  * Created by Lazdev OCR on 15/09/2021
 */

    /**
    * Dependency injector to get instance of services
    */
public class Di {
    private static final MeetingApiService service = new ImplMeetingApiService();

    /**
     * Get an instance on @{@link MeetingApiService}
     * @return
     */
    public static MeetingApiService getMeetingApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link MeetingApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MeetingApiService getNewInstanceApiService() {
        return new ImplMeetingApiService();
    }
}
