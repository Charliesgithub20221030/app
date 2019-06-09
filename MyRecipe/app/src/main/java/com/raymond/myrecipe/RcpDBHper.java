package com.raymond.myrecipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class RcpDBHper extends SQLiteOpenHelper {
//private static final String DBname = "MyRecipe.db";
//private static final int DBversion = 1;
    private static final String TBname = "Recipe";
    private static final String crTBsql = "CREATE TABLE " + TBname + "(" +
                                        "_id  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        "_name VARCHAR(50), " +
                                        "_category VARCHAR(50), " +
                                        "_level INTEGER, " +
                                        "_intro VARCHAR(255), " +
                                        "_ingredient VARCHAR(255), " +
                                        "_fact VARCHAR(100), " +
                                        "_method VARCHAR(255));";

    public RcpDBHper(Context context, String DBname, CursorFactory factory, int DBversion){
        super(context, "MyRecipe.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crTBsql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBname);
        onCreate(db);
    }

    public long insertRcp(String name, String category, int level, String intro, String ingredient, String method, String fact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues rcp = new ContentValues();
        rcp.put("_name", name);
        rcp.put("_category", category);
        rcp.put("_level", level);
        rcp.put("_intro", intro);
        rcp.put("_ingredient", ingredient);
        rcp.put("_fact", fact);
        rcp.put("_method", method);
        long rowID = db.insert(TBname, null, rcp);
        db.close();
        return rowID;
    }

    public void buildData(){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues[] rcp = new ContentValues[11];
        for(int i=0; i<rcp.length; i++){
            rcp[i] = new ContentValues();
        }

        rcp[0].put("_name", "Chicken Katsu");
        rcp[0].put("_category", "Japanese Dish");
        rcp[0].put("_level", "4");
        rcp[0].put("_intro", " Japanese style fried chicken.\n It can also be used to make Tonkatsu, just use pork cutlets instead of chicken. ");
        rcp[0].put("_ingredient", "1. 4 Skinless chicken breast halves \n2. Salt and pepper to taste \n3. 2 tablespoons all-purpose flour \n4. 1 egg, beaten \n5. 1 cup panko bread crumbs \6. 1 cup oil for frying, or as needed");
        rcp[0].put("_fact", " Per Serving: \n 297 calories; 11.4 g fat; 22.2 g carbohydrates; 31.2 g protein; 118 mg cholesterol; 251 mg sodium. ");
        rcp[0].put("_method", "1. Season the chicken breasts on both sides with salt and pepper. \n2. Place the flour, egg and panko crumbs into shallow dishes. \n3. Coat the chicken breasts in flour, shaking off any excess. \n4. Dip them into the egg, and then press into the panko crumbs until well coated on both sides. \n5. Heat oil in a large skillet over medium-high heat. \6. Place chicken in the hot oil, and cook 3 or 4 minutes per side, or until golden brown.");

        rcp[1].put("_name", "Miso Soup");
        rcp[1].put("_category", "Japanese Dish");
        rcp[1].put("_level", "1");
        rcp[1].put("_intro", " Dashi is a basic stock used in Japanese cooking which is made by boiling dried kelp (seaweed) and dried bonito (fish). \n Yellow miso is sweet and creamy, red miso is stronger and saltier.");
        rcp[1].put("_ingredient", "1. 2 teaspoons dashi granules\n2. 4 cups water\n3. 3 tablespoons miso paste \n4. 1 (8 ounce) package silken tofu, diced \n5. 2 green onions, sliced diagonally into 1/2 inch pieces");
        rcp[1].put("_fact", " Per Serving: \n 63 calories; 2.3 g fat; 5.3 g carbohydrates; 5.5 g protein; 0 mg cholesterol; 513 mg sodium.");
        rcp[1].put("_method", "1. Over medium-high heat, combine dashi granules and water.\n2. Bring to a boil.\n3. Reduce heat to medium, and whisk in the miso paste. \n4. Stir in tofu. \n5. Separate the layers of the green onions, and add them to the soup. \n6. Simmer gently for 2 to 3 minutes before serving.");

        rcp[2].put("_name", "Japanese Tamago Egg");
        rcp[2].put("_category", "Japanese Dish");
        rcp[2].put("_level", "3");
        rcp[2].put("_intro", "Tamago egg is classic Japanese folded omelet sometimes called tamagoyaki. \n The omelet is sweet, has a light texture, and works well when served over sushi rice and with soy and wasabi sauce for dipping.");
        rcp[2].put("_ingredient", "1. 4 eggs\n2. 1/4 cup prepared dashi stock \n3. 1 tablespoon white sugar \n4. 1 teaspoon mirin (Japanese sweet wine)\n5. 1/2 teaspoon soy sauce \n6 1/2 teaspoon vegetable oil, or more as needed");
        rcp[2].put("_fact", " Per Serving: \n63 calories; 3.8 g fat; 2.6 g carbohydrates; 4.4 g protein; 124 mg cholesterol; 87 mg sodium.");
        rcp[2].put("_method", "1. Beat eggs thoroughly in a bowl.\n2. Whisk in dashi stock, sugar, mirin, and soy sauce until sugar has dissolved.\n3. Place a nonstick skillet over medium heat.\n4. Oil the pan with vegetable oil. \n5. Pour a thin layer of egg mixture into the hot pan. \n6. When egg layer is firm on the bottom but still slightly liquid on top, lift up about 1 inch of the edge of the omelet and continue rolling the omelet to the end \n7. Push the roll to the edge of the skillet. \n8. Oil the skillet again if it looks dry. \n9.v Pour another thin layer of egg into the skillet and lift the roll to let the egg flow underneath the omelet roll. \n10. Fold the omelet roll over the new layer of egg, continuing to roll to the end as before. \n11. Push omelet to edge of skillet. \n12. Repeat until all egg mixture has been used.");

        rcp[3].put("_name", "Steamed Egg");
        rcp[3].put("_category", "Japanese Dish");
        rcp[3].put("_level", "1");
        rcp[3].put("_intro", "It has the sweetest aroma I have ever tasted in eggs using the least ingredients.");
        rcp[3].put("_ingredient", "1. 2 eggs\n2. 1 cup chicken or fish stock \n3. 1 dash sake\n4. 1/2 teaspoon soy sauce\n5. 1/2 cup chopped cooked chicken breast meat \n6. 1 shiitake mushroom, sliced into strips \n7. 2 sprigs fresh parsley, for garnish ");
        rcp[3].put("_fact", " Per Serving: \n 57 calories; 8.1 g fat; 3.3 g carbohydrates; 17.3 g protein; 213 mg cholesterol; 527 mg sodium.");
        rcp[3].put("_method", "1. Whisk eggs while slowly pouring in the chicken stock, sake, and soy sauce. \n2. Divide the mushroom and chicken evenly between two small tea cups. \n3. Pour the egg mixture into each cup until filled.\n4. Bring about 1 inch of water to a boil in a steamer or saucepan. \n5. Reduce heat to a simmer, and place cups into the steamer. \n6. Cover, and steam for 12 minutes, or until egg is firm but soft and silky like tofu.");

        rcp[4].put("_name", "Madeleines");
        rcp[4].put("_category", "French Dish");
        rcp[4].put("_level", "3");
        rcp[4].put("_intro", "The greatest afternoon tea cookie. \nYou can sprinkle the top with confectioners' sugar or dip the tips in chocolate.");
        rcp[4].put("_ingredient", "1. 2 eggs\n2. 1/2 teaspoon vanilla extract \n3. 1/2 teaspoon lemon zest \n4. 1 cup confectioners' sugar\n5. 3/4 cup all-purpose flour\n6. 1/4 teaspoon baking powder \n7. 1/2 cup butter, melted and cooled");
        rcp[4].put("_fact", " Per Serving: 148 calories; 8.6 g fat; 16.1 g carbohydrates; 1.9 g protein; 51 mg cholesterol; 77 mg sodium.");
        rcp[4].put("_method", "1. Preheat oven to 375 degrees F (190 degrees C). \n2. Mix eggs, vanilla and lemon zest \n3. Place a nonstick skillet over medium heat.\n4. Gradually beat in the confectioners' sugar until thick and satiny. \n5. Sift together the flour and baking powder. \n6. Sift one-fourth of the flour mixture over the egg mixture, gently fold in.\n7. Fold in the remaining flour by fourths. \n8. Then fold in the melted and cooled butter. \n9. Spoon batter into the prepared molds, filling 3/4 full.\n10. Bake at 375 degrees F (190 degrees C) for 10 to 12 minutes or until the edges are golden. \n11. Cool in molds on a rack for 1 minute. \n12. Sift confectioners' sugar over the tops or melt semi-sweet chocolate chips and dip the tips in the chocolate. ");

        rcp[5].put("_name", "French Crepes");
        rcp[5].put("_category", "French Dish");
        rcp[5].put("_level", "1");
        rcp[5].put("_intro", "This recipe can either be served traditional style with REAL maple syrup on top or cold with ice cream rolled into it and chocolate syrup on top as a desert.");
        rcp[5].put("_ingredient", "1. 1 cup all-purpose flour\n2. 1 egg\n3. 2 cups milk");
        rcp[5].put("_fact", " Per Serving: 110 calories; 2.3 g fat; 16.9 g carbohydrates; 5 g protein; 32 mg cholesterol; 39 mg sodium ");
        rcp[5].put("_method", "1. In a mixing bowl, combine flour, egg, and milk. \n2. Heat a large skillet over a medium-high heat. \n3. Pour about 1/3 cup of batter into the pan, lift the pan and turn it by rotating your wrist, spreading a PAPER THIN amount in the pan.\n4. Flip the crepe when it starts to bubble. \n5. When it is finished cooking, remove it and repeat this process with the remaining batter.");

        rcp[6].put("_name", "Lyonnaise Potatoes");
        rcp[6].put("_category", "French Dish");
        rcp[6].put("_level", "3");
        rcp[6].put("_intro", "This is a classic dish with sliced potatoes and onions.");
        rcp[6].put("_ingredient", "1. 2 pounds russet potatoes\n2. 2 tablespoons olive oil \n3. 4 onions, thinly sliced \n4. 2 tablespoons chopped garlic \n5. 1/2 cup butter \n6. salt to taste \n7. Ground white pepper, to taste \n8. 1 tablespoon finely minced fresh parsley");
        rcp[6].put("_fact", " Per Serving:  325 calories; 20.1 g fat; 34.6 g carbohydrates; 3.5 g protein; 41 mg cholesterol; 119 mg sodium.");
        rcp[6].put("_method", "1. Preheat oven to 400 degrees F (200 degrees C).\n2. Peel potatoes and cut into 1/2 inch slices.\n3. Place sliced potatoes in a pot and cover with water.\n4. Bring to a boil, then drain and set aside. \n5. Heat a large ovenproof skillet over medium-high heat.\n6. Pour in olive oil, then add onions.\n7. Saute until lightly caramelized, 8 to 10 minutes. \n8. Stir in garlic and saute until onions are deep brown and garlic is soft. \n9. Transfer mixture to a bowl.\n10. Place skillet back on stove over low heat. \n11. Melt butter, then cover bottom of pan with 1/3 of potatoes. \n12. Season with salt and pepper. \n13. Cover potatoes with 1/2 of onion mixture. \n14. Cover with 1/2 of remaining potatoes and season with salt and pepper. \n15. Spread with the rest of onion mixture. \16. Bake in preheated oven for 10 to 12 minutes, or until potatoes are tender and browned on top. \17. Remove from oven and use a spatula to carefully transfer potatoes to a serving platter. ");

        rcp[7].put("_name", "Korean Tofu Stew");
        rcp[7].put("_category", "Korean Dish");
        rcp[7].put("_level", "2");
        rcp[7].put("_intro", "This is an easy and authentic version of Korean tofu stew.\n Small pieces of meat or seafood can be added. \n It's thick and great in the winter and can be made very mild or very spicy.");
        rcp[7].put("_ingredient", "1. 1 teaspoon vegetable oil\n2. 1 teaspoon Korean chile powder\n3. 2 tablespoons ground beef (optional) \n4. 1 tablespoon Korean soy bean paste (doenjang) \n5. 1 cup water \n6. salt and pepper to taste \n7. 1 (12 ounce) package often tofu, drained and sliced \n8. 1 egg\n9. 1 teaspoon sesame seeds\n10. 1 green onion, chopped");
        rcp[7].put("_fact", " Per Serving: 242 calories; 16.5 g total fat; 99 mg cholesterol; 415 mg sodium. 7 g carbohydrates; 20 g protein");
        rcp[7].put("_method", "1. Heat the vegetable oil in a large saucepan over medium heat. \n2. Stir in the Korean chile powder and ground beef. \n3. Cook and stir until the beef is crumbly, evenly browned. \n4. Stir in the soy bean paste, coating the beef. \n5. Pour in the water and bring to a boil.\n6. Season with salt and pepper. \n7. Gently drop tofu into the soup and continue cooking until the tofu is heated through. \n8. Remove from heat and quickly add the egg into the soup, stirring gently. \n9. Garnish with sesame seeds and green onion. ");

        rcp[8].put("_name", "Indonesian Spiced Rice");
        rcp[8].put("_category", "Indonesian Dish");
        rcp[8].put("_level", "2");
        rcp[8].put("_intro", "This colorful spicy side dish goes perfect with kabobs, especially with cherry tomatoes; the color combo is most appealing.");
        rcp[8].put("_ingredient", "1. 3 tablespoons vegetable oil\n2. 1 large onion, chopped\n3. 2 jalapeno peppers, seeded and minced\n4. 2 cloves garlic, crushed \n5. 1 teaspoon ground turmeric \n6. 1/2 teaspoon ground cinnamon \n7. 2 cups uncooked long-grain white rice \n8. 2 (14.5 ounce) cans chicken broth\n9. 1 cup water\n10. 1 bay leaf\n11. 2 green onions, chopped");
        rcp[8].put("_fact", " Per Serving:  226 calories; 5.5 g fat; 39.8 g carbohydrates; 3.7 g protein; 0 mg cholesterol; 4 mg sodium");
        rcp[8].put("_method", "1. Heat oil in large, heavy pan over medium heat. \n2. Stir in onion, jalapeno peppers and garlic. \n3. Saute until onion is translucent; about 8 minutes. \n4. Stir turmeric, cinnamon, and rice into the pan; stir for 2 minutes. \n5. Mix in the chicken broth, water and bay leaf. \n6. Bring the mixture to a boil, reduce heat to low, cover and cook 20 minutes. \n7. Turn off the heat altogether and let sit for 5 minutes. \n8. Garnish with chopped green onion");

        rcp[9].put("_name", "Easy Chinese Corn Soup ");
        rcp[9].put("_category", "Chinese Dish");
        rcp[9].put("_level", "2");
        rcp[9].put("_intro", "This quick and easy soup is so good that I never bother ordering it from Chinese restaurants anymore.");
        rcp[9].put("_ingredient", "1. 1 (15 ounce) can cream style corn\n2. 1 (14.5 ounce) can low-sodium chicken broth\n3. 1 egg, beaten\n4. 1 tablespoon cornstarch \n5. 2 tablespoons water");
        rcp[9].put("_fact", " Per Serving: 121 calories; 1.9 g fat; 24.1 g carbohydrates; 5 g protein; 48 mg cholesterol; 409 mg sodium ");
        rcp[9].put("_method", "1. In a saucepan, combine the cream style corn and chicken broth. \n2. Bring to a boil over medium-high heat. \n3. In a small bowl, mix together the cornstarch and water; pour into the boiling corn soup, and continue cooking until thickened. \n4. Gradually add the beaten egg while stirring the soup. \n5. Remove from heat and serve.");

        rcp[10].put("_name", "Chinese Tea Leaf Eggs");
        rcp[10].put("_category", "Chinese Dish");
        rcp[10].put("_level", "2");
        rcp[10].put("_intro", "I combines hard-boiled eggs with the subtle flavor of anise and the deep brown hues of black tea and soy. \n The cracked patterns from the broken shells make these quite attractive.");
        rcp[10].put("_ingredient", "1. 8 eggs \n2. 1 teaspoon salt \n3. 3 cups water \n4. 1 tablespoon soy sauce \n5. 1 tablespoon black soy sauce\n6. 1/4 teaspoon salt\n7. 2 tablespoons black tea leaves\n8. 2 pods star anise\n9. 1 (2 inch) piece cinnamon stick\n10. 1 tablespoon tangerine zest ");
        rcp[10].put("_fact", " Per Serving: 76 calories; 5 g fat; 1.2 g carbohydrates; 6.6 g protein; 186 mg cholesterol; 659 mg sodium ");
        rcp[10].put("_method", "1. In a large saucepan, combine eggs and 1 teaspoon salt; cover with cold water. \n2. Bring to a boil, reduce heat, and simmer for 20 minutes.\n3. Remove from heat, drain, and cool. \n4. When cool, tap eggs with the back of a spoon to crack shells. \n5. In a large saucepan, combine 3 cups water, soy sauce, black soy sauce, salt, tea leaves, star anise, cinnamon stick, and tangerine zest. \n6. Bring to a boil, then reduce heat, cover, and simmer for 3 hours. \n7. Remove from heat, add eggs, and let steep for at least 8 hours. ");

//        rcp[11].put("_name", " My Best Clam Chowder ");
//        rcp[11].put("_category", "Chinese Dish");
//        rcp[11].put("_level", "3");
//        rcp[11].put("_intro", "A delicious, traditional, cream based chowder, this recipe calls for the standard chowder ingredients: onion, celery, potatoes, diced carrots, clams, and cream. \n A little red wine vinegar is added before serving for extra flavor");
//        rcp[11].put("_ingredient", "1. 3 (6.5 ounce) cans minced clams \n2. 1 cup minced onion \n3. 1 cup diced celery \n4. 2 cups cubed potatoes \n5. 1 cup diced carrots\n6. 3/4 cup butter\n7. 3/4 cup all-purpose flour\n8. 1 quart half-and-half cream\n9. 2 tablespoons red wine vinegar\n10. 1 1/2 teaspoons salt\n11. ground black pepper to taste");
//                rcp[11].put("_fact", " Per Serving: 501 calories; 32.7 g fat; 28.4 g carbohydrates; 23.9 g protein; 137 mg cholesterol; 712 mg sodium. ");
//        rcp[11].put("_method", "1. Drain juice from clams into a large skillet over the onions, celery, potatoes and carrots. \n2. Add water to cover, and cook over medium heat until tender. \n3. Meanwhile, in a large, heavy saucepan, melt the butter over medium heat. \n4. Whisk in flour until smooth. \n5. Whisk in cream and stir constantly until thick and smooth. \n6. Stir in vegetables and clam juice. \n7. Heat through, but do not boil.\n8. Stir in clams just before serving. \n9. If they cook too much they get tough. \n10. When clams are heated through, stir in vinegar, and season with salt and pepper. ");

        for(ContentValues c: rcp){
            db.insert(TBname, null, c);
        }
        db.close();
    }

    public ArrayList<Recipe> getRcpSet(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + TBname;
        Cursor rcpSet = db.rawQuery(sql, null);
        ArrayList<Recipe> rcpList = new ArrayList();
//        int colCount = rcpSet.getColumnCount();
        while(rcpSet.moveToNext()){
            int id = rcpSet.getInt(0);
            String name = rcpSet.getString(1);
            String category = rcpSet.getString(2);
            int level = rcpSet.getInt(3);
            String intro = rcpSet.getString(4);
            String ingredient = rcpSet.getString(5);
            String fact = rcpSet.getString(6);
            String method = rcpSet.getString(7);
            Recipe r = new Recipe(id, name, category, level, intro, ingredient, fact, method);
            rcpList.add(r);
        }


        rcpSet.close();
        db.close();
        return rcpList;
    }
}
