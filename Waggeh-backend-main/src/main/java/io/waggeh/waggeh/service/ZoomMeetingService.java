package io.waggeh.waggeh.service;

import io.waggeh.waggeh.model.requests.ZoomMeetingRequest;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;

import io.waggeh.waggeh.proxy.ZoomMeetingProxy;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ZoomMeetingService {

    private final ZoomMeetingProxy zoomMeetingProxy;

    public ZoomMeetingService(ZoomMeetingProxy zoomMeetingProxy) {
        this.zoomMeetingProxy = zoomMeetingProxy;
    }

    public ApiResponseWrapper<Map<String,String>> creatMeeting(ZoomMeetingRequest meetingRequest){
        String accessToken = zoomMeetingProxy.auth();

        ApiResponseWrapper<Map<String,String>> response = zoomMeetingProxy.createMeeting(meetingRequest,accessToken);

        return response;

    }

}
