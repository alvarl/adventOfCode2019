package ee.shuhari.day8;

import junit.framework.TestCase;

public class ImageProcessorTest extends TestCase {

  ImageProcessor processor = new ImageProcessor(3,2);

  public void testLayerCount() {
    processor.ingest("123456223456333456");
    assertEquals(3, processor.layers.size());
  }

  public void testFewestZeroes() {
    processor.ingest("123056223006300000");
    int[] metadataForFewestZeroesLayer = processor.getLayerWithFewestZeroes();
    assertEquals(1, metadataForFewestZeroesLayer[0]);
  }

  public void testTask() {
    processor = new ImageProcessor(25,6);
    processor.ingest(ImageProcessor.IMAGE_DATA);
    int[] layerWithFewestZeroes = processor.getLayerWithFewestZeroes();
    System.out.println(layerWithFewestZeroes[1] * layerWithFewestZeroes[2]);
  }


}
