import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

class ClassLoaderTest extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class hello = new ClassLoaderTest().findClass("/Users/luoxiaolong/workspace/JAVA-000/Week_01/question2/Hello.xlass");
            hello.getMethod("hello").invoke(hello.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected Class<?> findClass(String path) throws ClassNotFoundException {
        File classFile = new File(path);
        try (FileInputStream reader = new FileInputStream(classFile)) {
            int src;
            byte[] org = new byte[1024];
            int length = 0;
            while ((src = reader.read()) != -1) {
                org[length] = (byte) ((255 - src) & 0xFF);
                length++;
            }
            return defineClass("Hello", org, 0, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException(path);
    }
}