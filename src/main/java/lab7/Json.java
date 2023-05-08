package lab7;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Json {

    public Json(){

    }
    public void writeToFileJson(File file, List<Book> books){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file,books);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Book> readFromFileJson(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Book> books = objectMapper.readValue(file, new TypeReference<>(){});
            return books;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}