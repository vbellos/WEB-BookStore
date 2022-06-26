package com.unipi.BookStore.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Random;
import java.util.UUID;


public class IdGenerator {

    public int IntID()
    {
        UUID id = UUID.randomUUID();
        long hi = id.getMostSignificantBits();
        long lo = id.getLeastSignificantBits();
        byte[] bytes = ByteBuffer.allocate(16).putLong(hi).putLong(lo).array();
        BigInteger big = new BigInteger(bytes);
        String numericUuid = big.toString().replace('-','1');
        return Math.abs(big.intValue());
    }
    public String TimeId()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String a = IntID()+"";
        String b = timestamp+"";
        return a+b;
    }
    public String StringID()
    {
        byte[] array = new byte[8]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }
    public String BigID()
    {

        return StringID() + IntID();
    }
}
