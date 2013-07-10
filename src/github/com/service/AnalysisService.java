package github.com.service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
import org.atilika.kuromoji.Tokenizer.Builder;
import org.atilika.kuromoji.Tokenizer.Mode;

public class AnalysisService {

    private Builder builder = Tokenizer.builder();
    private static final Logger log = Logger.getLogger(AnalysisService.class.getName());

    public List<String> getSurfaceForm(String text) {
        List<String> results = new ArrayList<String>();
        try {
            builder.userDictionary("WEB-INF/userdic.txt");
        } catch (FileNotFoundException e) {
            log.warning("file not found");
        } catch (IOException e) {
            log.warning("IOException:" + e.getMessage());
        }
        builder.mode(Mode.NORMAL);
        Tokenizer tokenizer = builder.build();
        List<Token> tokens = tokenizer.tokenize(text);
        for (Token t : tokens) {
            if (isWord(t))
                results.add(t.getSurfaceForm());
        }
        return results;
    }
    private boolean isWord(Token token) {
        String[] parts = token.getPartOfSpeech().split(",");
        if (parts[0].matches(".*名詞.*") && checkPart1(parts) && !isSymbol(token)) {
            //log.info(token.getSurfaceForm() + " : " + Arrays.toString(parts));
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    private boolean checkPart1(String[] parts) {
        return !parts[1].matches(".*非自立.*") && 
               !parts[1].matches(".*代名詞.*") &&
               !parts[1].matches(".*接尾.*");
    }
    private boolean isSymbol(Token token) {
        Pattern pattern = Pattern.compile("^[\\p{Punct}]*$");
        Matcher matcher = pattern.matcher(token.getSurfaceForm());
        return matcher.matches();
    }
}
