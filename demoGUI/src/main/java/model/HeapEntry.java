package model;


public class HeapEntry {
    private String heapAddress;
    private String heapValue;

    public HeapEntry(String heapAddress, String heapValue) {
        this.heapAddress = heapAddress;
        this.heapValue = heapValue;
    }

    public String getHeapAddress() {
        return heapAddress;
    }

    public void setHeapAddress(String heapAddress) {
        this.heapAddress = heapAddress;
    }

    public String getHeapValue() {
        return heapValue;
    }

    public void setHeapValue(String heapValue) {
        this.heapValue = heapValue;
    }

    @Override
    public String toString() {
        return "HeapEntry{" +
                "heapAddress='" + heapAddress + '\'' +
                ", heapValue='" + heapValue + '\'' +
                '}';
    }
}
