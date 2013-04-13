
package intelligenceartificielle;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class SplashScreen extends JWindow {
	private static SplashScreen instance;
	private long minDuration;

	private SplashScreen(String imagePath, long minDuration) {
		super();
		this.minDuration = minDuration;
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		if (imagePath != null && !imagePath.equals("")) {
			ImageIcon img = new ImageIcon(imagePath);
			int imgHeight = img.getIconHeight();
			int imgWidth = img.getIconWidth();
			int imgX = (screenDimension.width - imgWidth) / 2;
			int imgY = (screenDimension.height - imgHeight) / 2;
			JLabel jl_img = new JLabel(img);
			jl_img.setPreferredSize(new Dimension(imgWidth, imgHeight));
			getContentPane().add(new JLabel(img));
			setLocation(imgX,imgY);
		} else {
			setLocation(screenDimension.width / 2, screenDimension.height / 2);
		}
		pack();
		setVisible(true);
		long tEnd = System.currentTimeMillis() + minDuration;
		while (System.currentTimeMillis() < tEnd) {
		}
	}

	public static SplashScreen getInstance(String imagePath, long minDuration) {
		if (instance == null) {
			instance = new SplashScreen(imagePath, minDuration);
		}
		return instance;
	}

	public static SplashScreen getInstance(String imagePath) {
		if (instance == null) {
			instance = new SplashScreen(imagePath, 0);
		}
		return instance;
	}

	public static SplashScreen getInstance() {
		return getInstance(null, 0);
	}

	public void end() {
		setVisible(false);
		dispose();
	}

}