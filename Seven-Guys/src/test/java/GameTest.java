
import edu.sdccd.cisc190.altscenes.Five1Morning;
import edu.sdccd.cisc190.altscenes.FiveAttackWater;
import edu.sdccd.cisc190.altscenes.FiveAttackWaterMore;
import edu.sdccd.cisc190.altscenes.FiveDodge;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.util.concurrent.CountDownLatch;

class GameTest {

    private void runOnJavaFXThread(Runnable task) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            task.run();
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testSceneCreation() throws InterruptedException {
        runOnJavaFXThread(() -> {
            Stage mockStage = new Stage();
            Five1Morning scene = new Five1Morning(mockStage);
            assertNotNull(scene.getScene());
        });
    }

    @Test
    void testButtonCreation() throws InterruptedException {
        runOnJavaFXThread(() -> {
            Stage mockStage = new Stage();
            FiveAttackWater scene = new FiveAttackWater(mockStage);
            assertNotNull(scene.getScene());
        });
    }

    @Test
    void testGameStatusText() throws InterruptedException {
        runOnJavaFXThread(() -> {
            Stage mockStage = new Stage();
            FiveDodge scene = new FiveDodge(mockStage);
            assertTrue(scene.getScene().getRoot().lookup(".text").toString().contains("You successfully dodged the attack"));
        });
    }

    @Test
    void testStatsUpdating() throws InterruptedException {
        runOnJavaFXThread(() -> {
            Stage mockStage = new Stage();
            FiveAttackWaterMore scene = new FiveAttackWaterMore(mockStage);
            scene.updateStats();
            assertTrue(scene.getScene().getRoot().lookup(".text").toString().contains("Conviction:"));
        });
    }
}
