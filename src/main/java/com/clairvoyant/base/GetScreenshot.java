package com.clairvoyant.base;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.clairvoyant.GenericUtils.DateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static com.clairvoyant.base.Constants.*;

public class GetScreenshot {


    public static String captureFailed(String testclassName, String screenShotName, WebDriver driver) throws IOException {
        String destPath = "";

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        destPath = OUTPUT_DIR + TEST_SCREENSHOT_DIR + File.separator + "failedScreenshot" + File.separator + testclassName + File.separator + screenShotName + ".png";
        File destination = new File(destPath);
        FileUtils.copyFile(source, destination);
        return destPath;
    }

    public static String capturePassed(String testclassName, String screenShotName, WebDriver driver) throws IOException {
        String destPath = "";


        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        destPath = OUTPUT_DIR + TEST_SCREENSHOT_DIR + File.separator + "passedScreenshot" + File.separator + testclassName + File.separator + screenShotName + ".png";

        /*Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(),"PNG",new File(destPath));
*/

        //Shutterbug.shootPage(driver,ScrollStrategy.BOTH_DIRECTIONS).save(destPath);

        File destination = new File(destPath);

        FileUtils.copyFile(source, destination);
        return destPath;


    }
}
