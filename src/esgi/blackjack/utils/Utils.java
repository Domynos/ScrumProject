package esgi.blackjack.utils;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import esgi.blackjack.main.Application;
import esgi.blackjack.windows.MainWindow;

public class Utils {

	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public static int randCard(){
		return Utils.randInt(0, 12)+13*(Utils.randInt(0, 3));
	}

	public static void restartApplication()
	{
		StringBuilder cmd = new StringBuilder();
        cmd.append(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java ");
        for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
            cmd.append(jvmArg + " ");
        }
        cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
        cmd.append(Application.class.getName()).append(" ");
       /* for (String arg : args) {
            cmd.append(arg).append(" ");
        }*/
        try {
			Runtime.getRuntime().exec(cmd.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.exit(0);
	}
}
