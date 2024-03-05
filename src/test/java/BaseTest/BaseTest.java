package BaseTest;

import Helpers.Helper;
import listeners.TestListener;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class BaseTest {
    @BeforeClass
    public void initializeDriver() throws Exception {
        Helper.createInstance();
        String className = getTestClassName();
        /*MyScreenRecorder.startRecording(className);*/
    }

    public String getTestClassName() throws Exception {
        String className = this.getClass().getSimpleName();
        return className;
    }


    /*@AfterClass
    public void close() throws Exception {
        Helper.driver.quit();
        *//*MyScreenRecorder.stopRecording();*//*
    }*/
}