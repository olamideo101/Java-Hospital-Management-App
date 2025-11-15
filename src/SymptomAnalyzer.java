import java.util.HashMap;
import java.util.Map;

public class SymptomAnalyzer {

    private static final Map<String, String> symptomMap = new HashMap<>();

    static {
        symptomMap.put("cough", "General Medicine");
        symptomMap.put("fever", "General Medicine");
        symptomMap.put("cold", "General Medicine");
        symptomMap.put("flu", "General Medicine");

        symptomMap.put("stomach", "Gastroenterology");
        symptomMap.put("vomit", "Gastroenterology");
        symptomMap.put("diarrhea", "Gastroenterology");

        symptomMap.put("chest", "Cardiology");
        symptomMap.put("heart", "Cardiology");
        symptomMap.put("shortness of breath", "Cardiology");

        symptomMap.put("rash", "Dermatology");
        symptomMap.put("itch", "Dermatology");
        symptomMap.put("acne", "Dermatology");

        symptomMap.put("depression", "Psychiatry");
        symptomMap.put("anxiety", "Psychiatry");
        symptomMap.put("stress", "Psychiatry");

        symptomMap.put("pregnancy", "OBGYN");
        symptomMap.put("period", "OBGYN");
        symptomMap.put("cramps", "OBGYN");
    }

    public static String detectSpecialty(String symptomsText) {
        if (symptomsText == null) return "General Medicine";
        String lower = symptomsText.toLowerCase();

        for (String key : symptomMap.keySet()) {
            if (lower.contains(key)) {
                return symptomMap.get(key);
            }
        }

        return "General Medicine";
    }
}
