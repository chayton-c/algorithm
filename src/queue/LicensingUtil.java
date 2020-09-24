package queue;

import javax.crypto.Cipher;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Scanner;

/**
 * @author hood  2020/4/30
 */
public class LicensingUtil {

    // rsa私钥用于解密
    private static final String PROJECT_NAME_LKJ = "LKJ"; // 铁路lkjetms
    private static final String PROJECT_NAME_POWER_PANT = "powerPlant"; // 电厂
    private static final String EDUCATION = "education"; // 教育
    private static final String RSA_PUBLIC_KEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCN5F5TwiBxbX8f3dqPLvA6zQorIOuBdwJlqG2LgAusFfGdsKyfke9nXN9nN1EBEWNxHwelpqABwnFcopmS0tJlAfImLsol8LVocJCah7GdZx7grD7UnYirqzdTK1F4fnz3Ggkgw7HSju9o4j9YVDv4/gNMA3Fa2mahhZ9x5NykEwIDAQAB";
    private static final String RSA_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI3kXlPCIHFtfx" +
            "/d2o8u8DrNCisg64F3AmWobYuAC6wV8Z2wrJ+R72dc32c3UQERY3EfB6WmoAHCcVyimZLS0mUB8iYuyiXwtWhwkJqHsZ1nHuCsPtSdiKurN1MrUXh" +
            "+fPcaCSDDsdKO72jiP1hUO/j+A0wDcVraZqGFn3Hk3KQTAgMBAAECgYBBHmZ/8B6tj8AA4vNUNQmBIuqgwnnyr6iE+RhVJk21C1kYsqV2kiYZ/NB2cTeW0uEdYE1FI8RZKmCWT" +
            "/VFIcX2cE7vnUR/+oD5a1mIGaXPLS9QTUQG4ruhw3oJvjy3bC6wPVk34WaLIBV05g2DcsplFhR73AVZFBeJx5ms5+j/aQJBAL8eq/QqRRX0gtVT4pZxaE/lnaGqgFIAix" +
            "/fqmZg1sPtEudRDPLlJIGuru7StVmdsQdJhwf7t4weLz/PC7dI9N0CQQC+D4suoYB+9R/BDdvktKViAF3w8tKQo/9+R2rfFVYP/9s+ZJYWUVgODEdbPEdTHz+Kt" +
            "/LSIm3DyY7neAdQAbWvAkAD3S3i0Nv2tqvSflCtc/uE6gKG2zie783gRhhe24RcD7X7IUNewc08Jfj5cR8jgbGrkQv+2FL8rTuNUbVN7latAkAC8kq" +
            "+FlLfpP0xCP33tbU6p7bNHXaWN3SmiITnoHUNGnJTrUuGXQx4a0hP6cdRSpK5m9Bv4YjCGEXW4M18OAqRAkEArLSsNmmT10qLk0tlkrFjYIFjUhkZu2BqF8DLIr6bnLDreh4GKsod+19LC1mAYqa/lvbvkEh158R1tGrIdIA+ww==";


    public static void main(String[] args) throws Exception {
        String machineCode = "903eed1b-1e15-4d84-8d28-b7463667eaff-1C-A0-B8-7E-7C-53-C099-0865";
//        String machineCode = "903eed1b-1e15-4d84-8d28-b7463667eaff-7C-B2-7D-2D-AC-27-C099-0865";
        String endDateStr = "2020-12-16";
        String projectName = "LKJ";

        long time = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr).getTime();

        System.out.println("cert:");
        System.out.println(createKeys(machineCode, time, projectName));
    }

    private static String createKeys(String machineCode, long timestamp, String projectName) throws Exception {
        return RSA_PRIVATE_KEY + "\n" + // 私钥c
                encrypt(machineCode, RSA_PUBLIC_KEY) + "\n" + // 机器码
                encrypt(projectName, RSA_PUBLIC_KEY) + "\n" + // 项目名
                encrypt(timestamp + "", RSA_PUBLIC_KEY); // 生效时间
    }

    private static String getMachineCode() throws IOException {
        Process process = Runtime.getRuntime().exec(new String[] {"wmic", "cpu", "get", "ProcessorId"});
        process.getOutputStream().close();
        Scanner sc = new Scanner(process.getInputStream());
        String property = sc.next();
        String serial = sc.next();
        return serial;
    }

    /**
     * RSA公钥加密
     *
     * @param str
     *            加密字符串
     * @param publicKey
     *            公钥
     * @return 密文
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey ) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        Base64.Encoder encoder = Base64.getEncoder();

        //base64编码的公钥
        byte[] decoded = decoder.decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return encoder.encodeToString(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }

}
