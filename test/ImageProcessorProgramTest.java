import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the script.
 */
public class ImageProcessorProgramTest {

  Readable rd;


  @Before
  public void init() throws IOException {
    String input = "";
    BufferedReader br;

    br = new BufferedReader(new FileReader("/Applications/IntelliJ project " +
            "folders/ImageProcessing/ImageProcessingTeam/res/scriptRun.txt"));

    String contentLine = br.readLine();
    input = input + contentLine + "\n";
    while (contentLine != null) {

      System.out.println(contentLine);

      contentLine = br.readLine();
      input = input + contentLine + "\n";
    }

    rd = new StringReader(input);
  }

  @Test
  public void testScript() throws IOException {
    ImageProcessorProgram.main(new String[]{"/Applications/IntelliJ project "
            + "folders/ImageProcessing/ImageProcessingTeam/res/scriptRun.txt"});
    assertEquals("Successfully rendered!\n"
            + "Successfully rendered!\n" +
            "Done!\n" +
            "Successfully rendered!\n" +
            "Done!\n" +
            "Successfully rendered!\n" +
            "Done!\n" +
            "Successfully rendered!\n" +
            "Done!\n" +
            "Successfully rendered!\n" +
            "Done!\n" +
            "Successfully rendered!\n" +
            "Done!\n" +
            "Successfully rendered!\n" +
            "Done!\n" +
            "Successfully rendered!\n" +
            "Done!\n" +
            "Successfully rendered!\n" +
            "Done!\n" +
            "Successfully rendered!\n" +
            "Done!\n" +
            "Successfully rendered!\n" +
            "Done!\n" +
            "Successfully rendered!\n" +
            "Done!", rd.toString());
  }
}