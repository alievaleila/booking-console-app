package az.edu.turing.domain.dao.impl.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil<T> {
    private final String fileName;

    public FileUtil(String fileName) {
        this.fileName = fileName;
    }

    public void writeObjectToFile(List<T> entityList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(entityList);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public List<T> readObjectFromFile() {
        List<T> entityList = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return entityList;
        }
        try (ObjectInputStream fileInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            entityList = (List<T>) fileInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entityList;
    }
}