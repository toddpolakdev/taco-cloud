package tacos.controllers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

import tacos.domain.Ingredient;
import tacos.repository.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepo;

    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(@NonNull String id) {
        return ingredientRepo.findById(id).orElse(null);
    }

    // private Map<String, Ingredient> ingredientMap = new HashMap<>();

    // public IngredientByIdConverter() {
    // ingredientMap.put("FLTO", new Ingredient("FLTO", "Flour Tortilla",
    // Type.WRAP));
    // ingredientMap.put("COTO", new Ingredient("COTO", "Corn Tortilla",
    // Type.WRAP));
    // ingredientMap.put("GRBF", new Ingredient("GRBF", "Ground Beef",
    // Type.PROTEIN));
    // ingredientMap.put("CARN", new Ingredient("CARN", "Carnitas", Type.PROTEIN));
    // ingredientMap.put("CHKN", new Ingredient("CHKN", "Chicken", Type.PROTEIN));
    // ingredientMap.put("TMTO", new Ingredient("TMTO", "Diced Tomatoes",
    // Type.VEGGIES));
    // ingredientMap.put("LETC", new Ingredient("LETC", "Lettuce", Type.VEGGIES));
    // ingredientMap.put("ONIN", new Ingredient("ONIN", "Onion", Type.VEGGIES));
    // ingredientMap.put("CHED", new Ingredient("CHED", "Cheddar", Type.CHEESE));
    // ingredientMap.put("JACK", new Ingredient("JACK", "Monterrey Jack",
    // Type.CHEESE));
    // ingredientMap.put("SLSA", new Ingredient("SLSA", "Salsa", Type.SAUCE));
    // ingredientMap.put("SRCR", new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
    // }

    // @Override
    // public Ingredient convert(@NonNull String id) {
    // return ingredientMap.get(id);
    // }
}
