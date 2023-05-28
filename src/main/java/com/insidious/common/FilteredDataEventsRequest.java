package com.insidious.common;


import java.util.List;

public class FilteredDataEventsRequest {
    private String sessionId;

    private long threadId = -1;
    private long nanotime = -1;

    private List<Long> valueId;

    private PageInfo pageInfo;
    private long objectId = -1;

    public static FilteredDataEventsRequest copyOf(FilteredDataEventsRequest request) {
        FilteredDataEventsRequest cloneRequest = new FilteredDataEventsRequest();

        cloneRequest.setNanotime(request.getNanotime());
        cloneRequest.setThreadId(request.getThreadId());
        cloneRequest.setProbeId(request.getProbeId());
        cloneRequest.setSessionId(request.getSessionId());
        cloneRequest.setObjectId(request.getObjectId());
        cloneRequest.setSortOrder(request.getSortOrder());
        PageInfo pageInfo = request.getPageInfo();
        cloneRequest.setPageInfo(new PageInfo(pageInfo.number, pageInfo.size, pageInfo.order));
        cloneRequest.setValueId(request.getValueId());

        return cloneRequest;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    private String sortOrder;
    private int probeId;


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public List<Long> getValueId() {
        return valueId;
    }

    public void setValueId(List<Long> valueId) {
        this.valueId = valueId;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setNanotime(long nanotime) {
        this.nanotime = nanotime;
    }

    public long getNanotime() {
        return nanotime;
    }

    public void setProbeId(int probeId) {
        this.probeId = probeId;
    }

    public int getProbeId() {
        return probeId;
    }

}
