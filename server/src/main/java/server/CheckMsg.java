package server;

public class CheckMsg {
    public static String result (String Msg) {
        var indexTwoPoint = Msg.indexOf(":");
        var bodyMsg  = Msg.substring(indexTwoPoint+1);
        var bodyMsgleght = bodyMsg.length();
        int headerBody;
        try {
            headerBody = Integer.parseInt(Msg.substring(0, indexTwoPoint));
            if (headerBody==bodyMsgleght){
                return "2:ok";
            } else {
                return "3:err";
            }
        }
        catch (NumberFormatException e) {
            return "3:err";
        }
    }
}
