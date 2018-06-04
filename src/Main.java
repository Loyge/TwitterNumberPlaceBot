import twitter4j.*;

import java.util.List;

public class Main {

    public static void main(String args[]) throws Exception {
        Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statuses = twitter.getMentionsTimeline();
        String inputString;
        for(Status status: statuses){ //たいむらいんちょっとさかのぼるてきな
            if(!status.isFavorited()){ //ふぁぼってなかったら
                twitter.createFavorite(status.getId()); //2回処理しないようにふぁぼる
                inputString = status.getText().replace("\r\n","").replace("\n","").replace("@NumPBot","").replace(" ",""); //ツイートの中身整える
                if(inputString.length() == 81 && inputString.matches("[0-9]*")){ //ナンプレの形式にあってるか
                    twitter.updateStatus(new StatusUpdate("@"+ status.getUser().getScreenName()+"\n"+show(inputString)).inReplyToStatusId(status.getId()));//解いてリプ！
                    System.out.println("Solved! : "+ status.getUser()); //解いたことだしとこ
                }else{
                    twitter.updateStatus(new StatusUpdate("@"+ status.getUser().getScreenName()+" error!").inReplyToStatusId(status.getId())); //形式がダメってリプ！
                }
            }
        }
    }

    private static String show(String input) throws Exception {
        int numberPlace[][] = new int[9][9];
        for(int i = 0;i < 9;i++){
            for(int j = 0;j < 9; j++){
                numberPlace[i][j] = Integer.parseInt(String.valueOf(input.charAt(i*9+j)));
            }
        }
        numberPlace = SolveNumberPlace.INSTANCE.solve(numberPlace);
        String output = "";
        for(int i = 0; i < 9; i++){
            output = output.concat("\r\n");
            for(int j = 0; j < 9; j++){
                output = output.concat(String.valueOf(numberPlace[i][j]));
            }
        }
        return output;
    }
}
