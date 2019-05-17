// Decompiled by DJ v3.12.12.100 Copyright 2015 Atanas Neshkov  Date: 2019/5/8 �U�� 11:54:41
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   InvoiceQRCodeEncrypter.java

import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class InvoiceQRCodeEncrypter
{

    public static void main(String args[])
        throws Exception
    {
        String s = "";
        String s1 = "";
        int i = 1;
        FileReader filereader = new FileReader("D:\\Invoice.txt");
        for(BufferedReader bufferedreader = new BufferedReader(filereader); bufferedreader.ready();)
            if(i == 1)
            {
                s = bufferedreader.readLine();
                i++;
            } else
            {
                s1 = bufferedreader.readLine();
            }

        filereader.close();
        InvoiceQRCodeEncrypter invoiceqrcodeencrypter = new InvoiceQRCodeEncrypter(s);
        String s2 = invoiceqrcodeencrypter.encode(s1);
        FileWriter filewriter = new FileWriter("D:\\QRCodeEncrypter.txt");
        filewriter.write(s2);
        filewriter.flush();
        filewriter.close();
    }

    public InvoiceQRCodeEncrypter(String s)
        throws Exception
    {
        ivParameterSpec = new IvParameterSpec(DatatypeConverter.parseBase64Binary("Dt8lyToo17X/XkXaQvihuA=="));
        secretKeySpec = new SecretKeySpec(DatatypeConverter.parseHexBinary(s), "AES");
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    }

    public String encode(String s)
        throws Exception
    {
        cipher.init(1, secretKeySpec, ivParameterSpec);
        byte abyte0[] = cipher.doFinal(s.getBytes());
        String s1 = new String(DatatypeConverter.printBase64Binary(abyte0));
        return s1;
    }

    public String decode(String s)
        throws Exception
    {
        cipher.init(2, secretKeySpec, ivParameterSpec);
        byte abyte0[] = DatatypeConverter.parseBase64Binary(s);
        String s1 = new String(cipher.doFinal(abyte0));
        return s1;
    }

    private static final String TYPE_SPEC = "AES";
    private static final String TYPE_INIT = "AES/CBC/PKCS5Padding";
    private static final String SPEC_KEY = "Dt8lyToo17X/XkXaQvihuA==";
    private SecretKeySpec secretKeySpec;
    private Cipher cipher;
    private IvParameterSpec ivParameterSpec;
}
