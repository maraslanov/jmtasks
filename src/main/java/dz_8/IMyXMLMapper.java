package dz_8;

public interface IMyXMLMapper {
    public Object readValue(String t) throws Exception;

    public String writeValue(Object obj) throws Exception;

    public void writeValue(String file, Object obj) throws Exception;
}
