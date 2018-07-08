package pac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HateNumber {
	/**入力＞嫌いな数字インデックス*/
	public static final int INDEX_HATE_NUMBER = 0;
	/**入力＞病室数インデックス*/
	public static final int INDEX_ROOM_NUMBER = 1;
	/**入力＞病室名インデックス*/
	public static final int INDEX_ROOM_NAME = 2;
	
	public static void main(String[] args) {
		
    	/** 嫌いな数字（1桁）*/
    	int intHateNumber = 0;
    	/**病室数*/
    	int intRoomNumber = 0;

        // 標準入力を取得
        List<String> inputStringList = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
        	
            while (true) {
                String inStr = br.readLine();
                if (inStr.isEmpty()) {
                    // 空文字でループ終了
                    break;
                }
                // 入力リストに追加
                inputStringList.add(inStr);
            }
            // 入力終了
            br.close();
        } catch (IOException e) {
            // 読み込み中にエラー
            System.out.println("読み込みエラーです。");
            System.exit(1);
        }

        // 入力数が要件を満たすかチェック
        if (inputStringList.size() < 1 + INDEX_ROOM_NAME) {
            // 入力行数が3行未満
            System.out.println("入力が不正です。最低3行の入力が必要です。入力ライン数：" + inputStringList.size());
            System.exit(1);
        }

        // 嫌いな数字の取得
        try {
            intHateNumber = Integer.valueOf(inputStringList.get(INDEX_HATE_NUMBER));
        } catch (NumberFormatException e) {
            // 数値でない
            System.out.println("嫌いな数字入力が数値ではありません。入力：" + inputStringList.get(INDEX_HATE_NUMBER));
            System.exit(1);
        }
        //嫌いな数字桁数チェック
        if (intHateNumber > 9) {
        	System.out.println("嫌いな数字は１桁で入力してください。入力：" + inputStringList.get(INDEX_HATE_NUMBER));
        	System.exit(1);
        }

        //病室数の取得
        try {
        	intRoomNumber = Integer.valueOf(inputStringList.get(INDEX_ROOM_NUMBER));
        } catch (NumberFormatException e) {
        	//数値でない
            System.out.println("病室数入力が数値ではありません。入力：" + inputStringList.get(INDEX_ROOM_NUMBER));
            System.exit(1);
        }
        if (intRoomNumber < 1) {
        	//入力が0
        	System.out.println("病室は必ず1部屋以上で入力してください。");
        	System.exit(1);
        }
        
        //病室名の取得
        List<String> hateNumberList = new ArrayList<String>();
        for (int i=0 ; i < (inputStringList.size() - INDEX_ROOM_NAME) ; i++) {
        	hateNumberList.add(inputStringList.get(INDEX_ROOM_NAME + i));
        }
        //病室数と病室名の整合性チェック
        if (hateNumberList.size() != intRoomNumber) {
        	System.out.println("病室数が不整合です。期待値：" + intRoomNumber + "　入力：" + (inputStringList.size()-2));
            System.exit(1);
        }
        
        //病室名に嫌いな数字が含まれている場合、それ以外の部屋名を表示
        String hateNumber = String.valueOf(intHateNumber);
        hateNumberList.stream()
        	.filter(str -> !str.contains(hateNumber))
        	.forEach(System.out::println);
        //表示できる部屋名が一つもない場合、noneを表示
        if (hateNumberList.stream().noneMatch(str -> !str.contains(hateNumber))) {
        	System.out.println("none");
        }
    }
}