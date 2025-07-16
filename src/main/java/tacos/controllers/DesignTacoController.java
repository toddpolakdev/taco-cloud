package tacos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tacos.domain.Ingredient;
import tacos.domain.Ingredient.Type;
import tacos.repository.IngredientRepository;
import tacos.domain.Taco;
import tacos.domain.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")

public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    // @ModelAttribute
    // public void addIngredientsToModel(Model model) {
    // List<Ingredient> ingredients = Arrays.asList(
    // new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
    // new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
    // new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
    // new Ingredient("CARN", "Carnitas", Type.PROTEIN),
    // new Ingredient("CHKN", "Chicken", Type.PROTEIN),
    // new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
    // new Ingredient("LETC", "Lettuce", Type.VEGGIES),
    // new Ingredient("ONIN", "Onion", Type.VEGGIES),
    // new Ingredient("CHED", "Cheddar", Type.CHEESE),
    // new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
    // new Ingredient("SLSA", "Salsa", Type.SAUCE),
    // new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

    // Type[] types = Ingredient.Type.values();
    // for (Type type : types) {
    // model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,
    // type));
    // }
    // }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {

        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    // private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type
    // type) {
    // return ingredients.stream()
    // .filter(x -> x.getType().equals(type))
    // .collect(Collectors.toList());
    // }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
        List<Ingredient> filtered = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getType().equals(type)) {
                filtered.add(ingredient);
            }
        }
        return filtered;
    }
}