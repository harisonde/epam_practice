package com.epam;

import java.io.*;

public class Serialization implements Serializable, Cloneable{

    private Integer count;

    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException {
        Serialization serialization = new Serialization();

        serialization.count = 100;

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("text.txt"));
        objectOutputStream.writeObject(serialization);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("text.txt"));
        Serialization serialization1 = (Serialization) objectInputStream.readObject();

        System.out.println(serialization1);

        Serialization clone = (Serialization) serialization.clone();

        System.out.println(clone);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
