package jlu.Img;



import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.pdfbox.multipdf.PageExtractor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;


public class PDFTOPICTURES {
	private static final int QUALITY_TYPE_HIGH=0;
	private static final int QUALITY_TYPE_MIDDLE=1;
	private static final int QUALITY_TYPE_LOW=2;
	private Image tempImage_1,tempImage_2,tempImage;
	private String pdfFilePath;
	private PDDocument pdfDocument;
	private PageExtractor pageExtractor;
	private PDFRenderer pdfRenderer;
	private int pdfCount;
	public PDFTOPICTURES(){}
	public PDFTOPICTURES(String pdfFilePath){
		this.pdfFilePath=pdfFilePath;
		try {
			init();
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * extract each PDF to a new-single PDF file,�ָ��ҳPDFΪ��ҳPDF
	 * @throws InvalidPasswordException
	 * @throws IOException
	 */
	public void extractPDF() throws InvalidPasswordException, IOException{
		String outpath=pdfFilePath.substring(0, pdfFilePath.lastIndexOf("."));
		PDDocument tempPDF = null;
		for(int i=0;i<pdfCount;i++){
			//the first page starts from 1
			pageExtractor=new PageExtractor(pdfDocument,i+1,i+1);
			tempPDF=pageExtractor.extract();
			//out 
			File file=new File(outpath+(i+1)+".pdf");
			tempPDF.save(file);
		}
		pdfDocument.close();
	}
	
	/**
	 * change each page of PDF to png or jpg for high quality by default,ת����ҳPDFΪͼƬ Ĭ�ϸ�����
	 * @throws IOException
	 */
	public void toPictures() throws IOException{
		int pageCounter=0;
		for (PDPage page : pdfDocument.getPages())
		{
		    // note that the page number parameter is zero based
		    BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, 300, ImageType.RGB);
		    // suffix in filename will be used as the file format
		    ImageIOUtil.writeImage(bim, pdfFilePath + "-" + (pageCounter++) + ".png", 4);
		}
		pdfDocument.close();
	}
	/**
	 * change each page of PDF to png or jpg,ת����ҳPDFΪͼƬ �����������ͼƬ�ķֱ���
	 * toPictures(800,1078) for example
	 * @param pixel_x
	 * @param pixel_y
	 * @throws IOException
	 */
	public void toPictures(int pixel_x,int pixel_y) throws IOException{
		int pageCounter=0;
		for (PDPage page : pdfDocument.getPages())
		{
			if(pageCounter >0){
				break;
			}
		    // note that the page number parameter is zero based
		    BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, 300, ImageType.RGB);
		    //deal with the pixel
		    int height=bim.getHeight();
		    int width=bim.getWidth();
		    //scale it
		    if(pixel_x>width){
		    	pixel_x=width;
		    }
		    if(pixel_y>height){
		    	pixel_y=height;
		    }
		    tempImage=bim.getScaledInstance(pixel_x, pixel_y, java.awt.Image.SCALE_SMOOTH);
		    int w=tempImage.getWidth(null);
		    int h=tempImage.getHeight(null);
		    BufferedImage biTemp = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		    Graphics gTemp = biTemp.getGraphics(); 
		    gTemp.drawImage(tempImage, 0, 0, null);
		    // out
		    ImageIOUtil.writeImage(biTemp, pdfFilePath + "-" + (pageCounter++) + ".png", 4);
		}
		pdfDocument.close();
	}
	/**
	 * change each page of PDF to png or jpg for high quality by default,ת����ҳPDFΪͼƬ Ĭ�ϸ����� 
	 * @param QUALITY_TYPE
	 * QUALITY_TYPE_HIGH,QUALITY_TYPE_MIDDLE,QUALITY_TYPE_LOW,
	 * QUALITY_TYPE_HIGH means to get a high quality of picture ,
	 * QUALITY_TYPE_MIDDLE means to get a middle quality of picture ,
	 * QUALITY_TYPE_LOW means to get a low quality of picture ,
	 * @throws IOException
	 */
	public void toPictures(int QUALITY_TYPE) throws IOException{
		int pageCounter=0;
		int qualityDPI;
		switch(QUALITY_TYPE){
		case QUALITY_TYPE_HIGH:qualityDPI=300;break;
		case QUALITY_TYPE_MIDDLE:qualityDPI=128;break;
		case QUALITY_TYPE_LOW:qualityDPI=72;break;
		default :qualityDPI=300;
		}
		for (PDPage page : pdfDocument.getPages())
		{
		    // note that the page number parameter is zero based
		    BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, qualityDPI, ImageType.RGB);
		    // suffix in filename will be used as the file format
		    ImageIOUtil.writeImage(bim, pdfFilePath + "-" + (pageCounter++) + ".png", 4);
		}
		pdfDocument.close();
	}
	
	/**
	 * change the each page of PDF to pictures and each page of PDF should be divided into two pictures with half of width,
	 * ��ÿҳpdfת��ΪͼƬ�����ҽ�һ��ͼƬ�з�Ϊ������ͼƬ����ͼƬ������ΪԭͼƬ��һ�룬����Ϊ���������룬ÿҳpdf�õ���ҳ��ͼ
	 * @throws IOException 
	 */
	public void toPictureWithHalfWidth() throws IOException{
		int pageCounter=0;
		for (PDPage page : pdfDocument.getPages())
		{
		    // note that the page number parameter is zero based
		    BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, 300, ImageType.RGB);
		    //deal with the pixel
		    int height=bim.getHeight();
		    int width=bim.getWidth();
		    //left-picture,���ͼ
		    BufferedImage bimLeft = new BufferedImage(width/2, height, BufferedImage.TYPE_INT_RGB);
		    bimLeft.getGraphics().drawImage(bim, 0, 0, width, height, null);
		    // suffix in filename will be used as the file format
		    ImageIOUtil.writeImage(bimLeft, pdfFilePath + (pageCounter) + "-left-" + ".png", 4);
		    //right-picture,�ұ�ͼ
		    BufferedImage bimRight = new BufferedImage(width/2, height, BufferedImage.TYPE_INT_RGB);
		    bimRight.getGraphics().drawImage(bim,  0, 0, width/2, height, width/2, 0, width, height, null);
		    ImageIOUtil.writeImage(bimRight, pdfFilePath + (pageCounter++) + "-right-"+ ".png", 4);
		}
		pdfDocument.close();
	}
	/**
	 * change the each page of PDF to pictures and each page of PDF should be divided into two pictures with half of width,
	 * ��ÿҳpdfת��ΪͼƬ�����ҽ�һ��ͼƬ�з�Ϊ������ͼƬ����ͼƬ������ΪԭͼƬ��һ�룬����Ϊ���������룬ÿҳpdf�õ���ҳ��ͼ
	 * toPictureWithHalfWidth(800,1078) for example
	 * @param pixel_x
	 * the size of pixle_x
	 * @param pixel_y
	 * the size of pixle_y
	 * @throws IOException
	 */
	public void toPictureWithHalfWidth(int pixel_x,int pixel_y) throws IOException{
		int pageCounter=0;
		for (PDPage page : pdfDocument.getPages())
		{
		    // note that the page number parameter is zero based
		    BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, 300, ImageType.RGB);
		    //deal with the pixel
		    int height=bim.getHeight();
		    int width=bim.getWidth();
		    //left-picture,���ͼ
		    BufferedImage bimLeft = new BufferedImage(width/2, height, BufferedImage.TYPE_INT_RGB);
		    bimLeft.getGraphics().drawImage(bim, 0, 0, width, height, null);
		    //scale it
		    if(pixel_x>width){
		    	pixel_x=width;
		    }
		    if(pixel_y>height){
		    	pixel_y=height;
		    }
		    tempImage_1=bimLeft.getScaledInstance(pixel_x, pixel_y, java.awt.Image.SCALE_SMOOTH);
		    int w=tempImage_1.getWidth(null);
		    int h=tempImage_1.getHeight(null);
		    BufferedImage bi_left = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		    Graphics g_left = bi_left.getGraphics(); 
		    g_left.drawImage(tempImage_1, 0, 0, null);
		    // out
		    ImageIO.write(bi_left,"png",new File(pdfFilePath + (pageCounter) + "-left-" + ".png"));
		    
		    //right-picture,�ұ�ͼ
		    BufferedImage bimRight = new BufferedImage(width/2, height, BufferedImage.TYPE_INT_RGB);
		    bimRight.getGraphics().drawImage(bim,  0, 0, width/2, height, width/2, 0, width, height, null);
		    //scale it
		    tempImage_2=bimRight.getScaledInstance(pixel_x, pixel_y, java.awt.Image.SCALE_SMOOTH);
		    BufferedImage bi_right = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		    Graphics g_right = bi_right.getGraphics(); 
		    g_right.drawImage(tempImage_2, 0, 0, null);
		    // out
		    ImageIO.write(bi_right,"png",new File(pdfFilePath + (pageCounter++) + "-right-" + ".png"));
		}
		pdfDocument.close();
	}
	
	private void init() throws InvalidPasswordException, IOException {
		pdfDocument= PDDocument.load(new File(pdfFilePath));
	    pdfRenderer = new PDFRenderer(pdfDocument);
	    pdfCount=pdfDocument.getNumberOfPages();
	}

}
