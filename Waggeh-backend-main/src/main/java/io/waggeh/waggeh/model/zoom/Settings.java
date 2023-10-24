package io.waggeh.waggeh.model.zoom;

import java.util.ArrayList;
import java.util.List;

public class Settings {
    private List<String> additionalDataCenterRegions = new ArrayList<String>();
    private Boolean allowMultipleDevices;
    private String alternativeHosts;
    private Boolean alternativeHostsEmailNotification;
    private Integer approvalType;
    private ApprovedOrDeniedCountriesOrRegions approvedOrDeniedCountriesOrRegions;
    private String audio;
    private String audioConferenceInfo;
    private String authenticationDomains;
    private List<AuthenticationException> authenticationException = new ArrayList<AuthenticationException>();
    private String authenticationOption;
    private String autoRecording;
    private BreakoutRoom breakoutRoom;
    private Integer calendarType;
    private Boolean closeRegistration;
    private String contactEmail;
    private String contactName;
    private Boolean emailNotification;
    private String encryptionType;
    private Boolean focusMode;
    private List<String> globalDialInCountries = new ArrayList<String>();
    private Boolean hostVideo;
    private Integer jbhTime;
    private Boolean joinBeforeHost;
    private LanguageInterpretation languageInterpretation;
    private SignLanguageInterpretation signLanguageInterpretation;
    private Boolean meetingAuthentication;
    private List<MeetingInvitee> meetingInvitees = new ArrayList<MeetingInvitee>();
    private Boolean muteUponEntry;
    private Boolean participantVideo;
    private Boolean privateMeeting;
    private Boolean registrantsConfirmationEmail;
    private Boolean registrantsEmailNotification;
    private Integer registrationType;
    private Boolean showShareButton;
    private Boolean usePmi;
    private Boolean waitingRoom;
    private Boolean watermark;
    private Boolean hostSaveVideoOrder;
    private Boolean alternativeHostUpdatePolls;

    public List<String> getAdditionalDataCenterRegions() {
        return additionalDataCenterRegions;
    }

    public void setAdditionalDataCenterRegions(List<String> additionalDataCenterRegions) {
        this.additionalDataCenterRegions = additionalDataCenterRegions;
    }

    public Boolean getAllowMultipleDevices() {
        return allowMultipleDevices;
    }

    public void setAllowMultipleDevices(Boolean allowMultipleDevices) {
        this.allowMultipleDevices = allowMultipleDevices;
    }

    public String getAlternativeHosts() {
        return alternativeHosts;
    }

    public void setAlternativeHosts(String alternativeHosts) {
        this.alternativeHosts = alternativeHosts;
    }

    public Boolean getAlternativeHostsEmailNotification() {
        return alternativeHostsEmailNotification;
    }

    public void setAlternativeHostsEmailNotification(Boolean alternativeHostsEmailNotification) {
        this.alternativeHostsEmailNotification = alternativeHostsEmailNotification;
    }

    public Integer getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(Integer approvalType) {
        this.approvalType = approvalType;
    }

    public ApprovedOrDeniedCountriesOrRegions getApprovedOrDeniedCountriesOrRegions() {
        return approvedOrDeniedCountriesOrRegions;
    }

    public void setApprovedOrDeniedCountriesOrRegions(ApprovedOrDeniedCountriesOrRegions approvedOrDeniedCountriesOrRegions) {
        this.approvedOrDeniedCountriesOrRegions = approvedOrDeniedCountriesOrRegions;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getAudioConferenceInfo() {
        return audioConferenceInfo;
    }

    public void setAudioConferenceInfo(String audioConferenceInfo) {
        this.audioConferenceInfo = audioConferenceInfo;
    }

    public String getAuthenticationDomains() {
        return authenticationDomains;
    }

    public void setAuthenticationDomains(String authenticationDomains) {
        this.authenticationDomains = authenticationDomains;
    }

    public List<AuthenticationException> getAuthenticationException() {
        return authenticationException;
    }

    public void setAuthenticationException(List<AuthenticationException> authenticationException) {
        this.authenticationException = authenticationException;
    }

    public String getAuthenticationOption() {
        return authenticationOption;
    }

    public void setAuthenticationOption(String authenticationOption) {
        this.authenticationOption = authenticationOption;
    }

    public String getAutoRecording() {
        return autoRecording;
    }

    public void setAutoRecording(String autoRecording) {
        this.autoRecording = autoRecording;
    }

    public BreakoutRoom getBreakoutRoom() {
        return breakoutRoom;
    }

    public void setBreakoutRoom(BreakoutRoom breakoutRoom) {
        this.breakoutRoom = breakoutRoom;
    }

    public Integer getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(Integer calendarType) {
        this.calendarType = calendarType;
    }

    public Boolean getCloseRegistration() {
        return closeRegistration;
    }

    public void setCloseRegistration(Boolean closeRegistration) {
        this.closeRegistration = closeRegistration;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Boolean getEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(Boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

    public String getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
    }

    public Boolean getFocusMode() {
        return focusMode;
    }

    public void setFocusMode(Boolean focusMode) {
        this.focusMode = focusMode;
    }

    public List<String> getGlobalDialInCountries() {
        return globalDialInCountries;
    }

    public void setGlobalDialInCountries(List<String> globalDialInCountries) {
        this.globalDialInCountries = globalDialInCountries;
    }

    public Boolean getHostVideo() {
        return hostVideo;
    }

    public void setHostVideo(Boolean hostVideo) {
        this.hostVideo = hostVideo;
    }

    public Integer getJbhTime() {
        return jbhTime;
    }

    public void setJbhTime(Integer jbhTime) {
        this.jbhTime = jbhTime;
    }

    public Boolean getJoinBeforeHost() {
        return joinBeforeHost;
    }

    public void setJoinBeforeHost(Boolean joinBeforeHost) {
        this.joinBeforeHost = joinBeforeHost;
    }

    public LanguageInterpretation getLanguageInterpretation() {
        return languageInterpretation;
    }

    public void setLanguageInterpretation(LanguageInterpretation languageInterpretation) {
        this.languageInterpretation = languageInterpretation;
    }

    public SignLanguageInterpretation getSignLanguageInterpretation() {
        return signLanguageInterpretation;
    }

    public void setSignLanguageInterpretation(SignLanguageInterpretation signLanguageInterpretation) {
        this.signLanguageInterpretation = signLanguageInterpretation;
    }

    public Boolean getMeetingAuthentication() {
        return meetingAuthentication;
    }

    public void setMeetingAuthentication(Boolean meetingAuthentication) {
        this.meetingAuthentication = meetingAuthentication;
    }

    public List<MeetingInvitee> getMeetingInvitees() {
        return meetingInvitees;
    }

    public void setMeetingInvitees(List<MeetingInvitee> meetingInvitees) {
        this.meetingInvitees = meetingInvitees;
    }

    public Boolean getMuteUponEntry() {
        return muteUponEntry;
    }

    public void setMuteUponEntry(Boolean muteUponEntry) {
        this.muteUponEntry = muteUponEntry;
    }

    public Boolean getParticipantVideo() {
        return participantVideo;
    }

    public void setParticipantVideo(Boolean participantVideo) {
        this.participantVideo = participantVideo;
    }

    public Boolean getPrivateMeeting() {
        return privateMeeting;
    }

    public void setPrivateMeeting(Boolean privateMeeting) {
        this.privateMeeting = privateMeeting;
    }

    public Boolean getRegistrantsConfirmationEmail() {
        return registrantsConfirmationEmail;
    }

    public void setRegistrantsConfirmationEmail(Boolean registrantsConfirmationEmail) {
        this.registrantsConfirmationEmail = registrantsConfirmationEmail;
    }

    public Boolean getRegistrantsEmailNotification() {
        return registrantsEmailNotification;
    }

    public void setRegistrantsEmailNotification(Boolean registrantsEmailNotification) {
        this.registrantsEmailNotification = registrantsEmailNotification;
    }

    public Integer getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(Integer registrationType) {
        this.registrationType = registrationType;
    }

    public Boolean getShowShareButton() {
        return showShareButton;
    }

    public void setShowShareButton(Boolean showShareButton) {
        this.showShareButton = showShareButton;
    }

    public Boolean getUsePmi() {
        return usePmi;
    }

    public void setUsePmi(Boolean usePmi) {
        this.usePmi = usePmi;
    }

    public Boolean getWaitingRoom() {
        return waitingRoom;
    }

    public void setWaitingRoom(Boolean waitingRoom) {
        this.waitingRoom = waitingRoom;
    }

    public Boolean getWatermark() {
        return watermark;
    }

    public void setWatermark(Boolean watermark) {
        this.watermark = watermark;
    }

    public Boolean getHostSaveVideoOrder() {
        return hostSaveVideoOrder;
    }

    public void setHostSaveVideoOrder(Boolean hostSaveVideoOrder) {
        this.hostSaveVideoOrder = hostSaveVideoOrder;
    }

    public Boolean getAlternativeHostUpdatePolls() {
        return alternativeHostUpdatePolls;
    }

    public void setAlternativeHostUpdatePolls(Boolean alternativeHostUpdatePolls) {
        this.alternativeHostUpdatePolls = alternativeHostUpdatePolls;
    }
}
