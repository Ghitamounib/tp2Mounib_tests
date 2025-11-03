package ma.emsi.mounib;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.googleai.GoogleAiEmbeddingModel;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.CosineSimilarity;
import java.time.Duration;

public class Test3 {
    public static void main(String[] args) {
        String llmKey = System.getenv("GEMINI_KEY");

        EmbeddingModel modele = GoogleAiEmbeddingModel.builder()
                .apiKey(llmKey)
                .modelName("gemini-embedding-001").
                taskType(GoogleAiEmbeddingModel.TaskType.SEMANTIC_SIMILARITY)
                .outputDimensionality(300)
                .timeout(Duration.ofSeconds(10)).
                build();

        String phrase1 = "Bonjour, comment allez-vous ?";
        String phrase2 = "Salut, quoi de neuf ?";

        Response<Embedding> reponse1 = modele.embed(phrase1);
        Response<Embedding> reponse2 = modele.embed(phrase2);
        Embedding emb1 = reponse1.content();
        Embedding emb2 = reponse2.content();
        double similarite = CosineSimilarity.between(emb1, emb2);
        System.out.println("Similarité cosinus : " + similarite);

        // === DEUX NOUVEAUX COUPLES (seulement ajoutés) ===
        String phrase3 = "Il pleut aujourd'hui.";
        String phrase4 = "Le soleil brille fort.";
        Response<Embedding> reponse3 = modele.embed(phrase3);
        Response<Embedding> reponse4 = modele.embed(phrase4);
        Embedding emb3 = reponse3.content();
        Embedding emb4 = reponse4.content();
        double similarite2 = CosineSimilarity.between(emb3, emb4);
        System.out.println("Similarité cosinus : " + similarite2);

        String phrase5 = "J'aime le chocolat.";
        String phrase6 = "J'adore le chocolat noir.";
        Response<Embedding> reponse5 = modele.embed(phrase5);
        Response<Embedding> reponse6 = modele.embed(phrase6);
        Embedding emb5 = reponse5.content();
        Embedding emb6 = reponse6.content();
        double similarite3 = CosineSimilarity.between(emb5, emb6);
        System.out.println("Similarité cosinus : " + similarite3);
    }
}