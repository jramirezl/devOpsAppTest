package co.edu.udem.userstoriesnlp.util;

import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document.Type;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Analyze {

    static Logger logger = LoggerFactory.getLogger(Analyze.class);

    public static @NonNull String analyzeEntitiesText(String text) {
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
            AnalyzeEntitiesRequest request =
                    AnalyzeEntitiesRequest.newBuilder()
                            .setDocument(doc)
                            .setEncodingType(EncodingType.UTF16)
                            .build();

            AnalyzeEntitiesResponse response = language.analyzeEntities(request);
            int count = 1;
            for (Entity entity : response.getEntitiesList()) {
                text = text.replaceAll(entity.getName(),"<"+entity.getName()+">"+count++);
            }
        } catch (Exception e) {
            logger.error("ERROR in analyzeEntitiesText:" + e.getMessage());
        }
        return text;
    }
}