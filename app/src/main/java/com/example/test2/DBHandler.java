package com.example.test2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String database_name = "lifeUnderTheSea.db";
    private String create_userTable = "CREATE TABLE user (user_ID INTEGER PRIMARY KEY, name VARCHAR(50), xpValue INTEGER, picture VARCHAR(100), isChanged BOOL DEFAULT 0);";
    private String create_quizTable = "CREATE TABLE quiz (quiz_ID INTEGER PRIMARY KEY, description VARCHAR(500), answer VARCHAR(200), isAttempted BOOL DEFAULT 0);";
    private String create_optionTable = "CREATE TABLE option (description VARCHAR(200), quiz_ID INTEGER);";
    private String create_sealifeTable = "CREATE TABLE sealife (name VARCHAR(30), description VARCHAR(2000), isCaptured BOOL DEFAULT 0);";

    private static final int database_version = 1;

    public DBHandler(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_userTable);
        sqLiteDatabase.execSQL(create_quizTable);
        sqLiteDatabase.execSQL(create_optionTable);
        sqLiteDatabase.execSQL(create_sealifeTable);
        insertQuizQuestion(sqLiteDatabase);
        insertOption(sqLiteDatabase);
        insertSealife(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS quiz");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS option");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS sealife");
        onCreate(sqLiteDatabase);
    }

    //insert quiz questions into database
    public void insertQuizQuestion(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("INSERT INTO quiz (quiz_ID, description, answer) VALUES ('1','What protects a crab’s body?','hard shell')");
        sqLiteDatabase.execSQL("INSERT INTO quiz (quiz_ID, description, answer) VALUES ('2','What crabs do when their shells become too small?','molt')");
        sqLiteDatabase.execSQL("INSERT INTO quiz (quiz_ID, description, answer) VALUES ('3','What kind of animals are turtles?','Reptiles')");
        sqLiteDatabase.execSQL("INSERT INTO quiz (quiz_ID, description, answer) VALUES ('4','Which of these does NOT describe a turtle?','Mammal')");
        sqLiteDatabase.execSQL("INSERT INTO quiz (quiz_ID, description, answer) VALUES ('5','Where do turtles lay their eggs?','All turtles lay their eggs on land')");
        sqLiteDatabase.execSQL("INSERT INTO quiz (quiz_ID, description, answer) VALUES ('6','Seahorses have a tail that curls forward - what do they use their tail for?','grabbing')");
        sqLiteDatabase.execSQL("INSERT INTO quiz (quiz_ID, description, answer) VALUES ('7','Which of the following do a seahorse and chameleon not have in common?','a long tongue')");
        sqLiteDatabase.execSQL("INSERT INTO quiz (quiz_ID, description, answer) VALUES ('8','What is the biggest threat to seahorses?','use in medicines')");
        sqLiteDatabase.execSQL("INSERT INTO quiz (quiz_ID, description, answer) VALUES ('9','Do sharks see in the dark night?','Yes, they can see in the dark')");
        sqLiteDatabase.execSQL("INSERT INTO quiz (quiz_ID, description, answer) VALUES ('10','Do jellyfish have brains or bones?','They do not have both of them')");
    }

    //insert question option into database
    public void insertOption(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('bones','1')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('hard shell','1')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('sharp spines','1')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('poison','1')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('molt','2')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('swim','2')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('migrate','2')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('pinch','2')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('Fish','3')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('Reptiles','3')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('Lizards','3')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('Mammals','3')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('Aquatic','4')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('Mammal','4')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('Egg-laying','4')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('Has a shell','4')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('They do not lay eggs, they give birth to live young','5')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('Sea turtles lay their eggs in the water','5')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('All turtles lay their eggs on land','5')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('They lay their eggs in a nest of twigs','5')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('swimming','6')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('grabbing','6')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('communication','6')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('attracting food','6')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('prehensile tail','7')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('a long tongue','7')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('eyes that move independently','7')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('can change color','7')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('aquarium hobbyists','8')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('loss of habitat','8')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('water pollution','8')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('use in medicines','8')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('Yes, they can see in the dark','9')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('Sharks cannot see at night, it is far too dark','9')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('No, they go to sleep at night','9')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('Yes,because their eyes send out light beams','9')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('They have brains, but they do not have bones','10')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('They have bones, but they do not have brains','10')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('They have both bones and brains','10')");
        sqLiteDatabase.execSQL("INSERT INTO option (description, quiz_ID) VALUES ('They do not have both of them','10')");
    }

    //insert sealife information into database
    public void insertSealife(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("INSERT INTO sealife (name, description) VALUES ('seahorse','Average lifespan of seahorse is 3 years. Seahorses prefer to swim in pairs with their tails linked together. However, they are even slower than snails, which is 150 cm per hour. It moves each of its eyes independently, so it can follow the activity of passing sea life without giving its presence away. Seahorses have no teeth and no stomach. Food passes through their digestive systems so quickly, they can consume 3,000 or more brine shrimp per day. Rarer still, they are among the only animal species on Earth in which the male bears the unborn young.')");
        sqLiteDatabase.execSQL("INSERT INTO sealife (name, description) VALUES ('jellyfish','Lifespan of jellyfish is 1 year. Their top speed is 8km/hour. They usually eat fish, shrimp, and even other species of jellyfish. Some jellyfish can glow in the dark to attract prey or distracting predators. Jelly do not have brains but nerve nets which sense changes in the environment and coordinate the animals responses. Jellyfish can clone themselves. If a jellyfish is cut in two, the pieces of the jellyfish can regenerate and create two new organisms.')");
        sqLiteDatabase.execSQL("INSERT INTO sealife (name, description) VALUES ('turtle','Lifespan of turtle is 80 years. These creatures date back to the time of the dinosaurs, over 200 million years ago. A turtle cannot come out of its shell. The turtle’s shell grows with them. This super-tough casing acts like a shield to protect them from predators – some turtles can even tuck their head up inside their shell for extra protection! Many baby turtles start life as carnivores but grow to eat more plants as they mature.')");
        sqLiteDatabase.execSQL("INSERT INTO sealife (name, description) VALUES ('shark','Average lifespan is 20 to 30 years. Sharks have been around for nearly 400 million years and were about 200 million years older than dinosaurs. Sharks cannot chew their food. If the meal is too large to swallow, they shake it from side to side to saw it into chunks. Most sharks never sleep because they have to constantly pump water through their mouth over their gills to breath or they will die. They have eyes similar to cats and can see better in dark and murky waters than other fish.')");
        sqLiteDatabase.execSQL("INSERT INTO sealife (name, description) VALUES ('crab','Average lifespan is 3 years. Crabs are also known as decapods because they have 10 legs. First pair of legs is modified into claws, called chelae. Crabs walk and swim sideways. Crabs breathe with the help of gills. That is why they need to stay close to the water even if they live mainly on the land. Crabs communicate via sound. They produce drumming and flapping sounds by using their claws and pincers.')");
    }

    public String getQuizQuestion(int quiz_ID) {
        String query = "SELECT description FROM quiz WHERE quiz_ID =" + "'" + quiz_ID + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return (cursor.getString(cursor.getColumnIndex("description")));
    }

    public ArrayList<String> getQuestionOption(int quiz_ID) {
        ArrayList<String> option = new ArrayList<>();
        String query = "SELECT description FROM option WHERE quiz_ID =" + "'" + quiz_ID + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                option.add(cursor.getString(cursor.getColumnIndex("description")));
            } while (cursor.moveToNext());
        }
        return option;
    }

    public String getQuizAnswer(int quiz_ID) {
        String query = "SELECT answer FROM quiz WHERE quiz_ID =" + "'" + quiz_ID + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return (cursor.getString(cursor.getColumnIndex("answer")));
    }

    public String getFunfact(String animalName) {
        String query = "SELECT description FROM sealife WHERE name =" + "'" + animalName + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return (cursor.getString(cursor.getColumnIndex("description")));
    }


    public ArrayList<String> getUserInfo() {
        ArrayList<String> userInfo = new ArrayList<>();
        String query = "SELECT * FROM user";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            userInfo.add(cursor.getString(cursor.getColumnIndex("name")));
            userInfo.add(cursor.getString(cursor.getColumnIndex("xpValue")));
            userInfo.add(cursor.getString(cursor.getColumnIndex("picture")));
            userInfo.add(cursor.getString(cursor.getColumnIndex("isChanged")));
        }
        return userInfo;
    }

    public void insertUserInfo(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_ID", 100);
        values.put("name", username);
        values.put("xpValue", 0);
        db.insert("user", null, values);
    }

    public void updateUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", username);
        db.update("user", values, "user_ID=100", null);
    }

    public void updateUserPicture(String pic_uri, boolean isChanged) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("picture", pic_uri);
        values.put("isChanged", isChanged);
        db.update("user", values, "user_ID=100", null);
    }

    public ArrayList<String> getSummary() {
        ArrayList<String> summary = new ArrayList<>();
        String query1 = "SELECT * FROM sealife WHERE isCaptured='1'";
        String query2 = "SELECT * FROM quiz WHERE isAttempted='1'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor1 = db.rawQuery(query1, null);
        Cursor cursor2 = db.rawQuery(query2, null);
        if (cursor1.moveToFirst()) {
            summary.add(Integer.toString(cursor1.getCount()));
        } else
            summary.add("0");
        if (cursor2.moveToFirst()) {
            summary.add(Integer.toString(cursor2.getCount()));
        } else
            summary.add("0");
        return summary;
    }

    public void updateXPvalue(int xp_award) {
        ArrayList<String> userInfo = getUserInfo();
        int current_xp = Integer.parseInt(userInfo.get(1));
        int latest_xp = current_xp + xp_award;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("xpValue", latest_xp);
        db.update("user", values, "user_ID=100", null);
    }

    public String checkIsAttempted(int quiz_ID, String sealife, char type) {
        String isAttempted = "";
        SQLiteDatabase db = this.getReadableDatabase();

        switch (type) {
            case 'Q':
                String query = "SELECT isAttempted FROM quiz WHERE quiz_ID =" + "'" + quiz_ID + "'";
                Cursor cursor = db.rawQuery(query, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    isAttempted = cursor.getString(cursor.getColumnIndex("isAttempted"));
                }
                break;
            case 'I':
                query = "SELECT isCaptured FROM sealife WHERE name='" + sealife + "'";
                cursor = db.rawQuery(query, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    isAttempted = cursor.getString(cursor.getColumnIndex("isCaptured"));
                }
                break;

        }
        return isAttempted;
    }


    public void setIsAttempted(int quiz_ID, String sealife, char type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        switch (type) {
            case 'Q':
                values.put("isAttempted", 1);
                db.update("quiz", values, "quiz_ID='" + quiz_ID + "'", null);
                break;
            case 'I':
                values.put("isCaptured", 1);
                db.update("sealife", values, "name='" + sealife + "'", null);
                break;
        }
    }

    public boolean checkIsAllAttempted(){
        boolean IsAllAttempted = false;
        String query = "SELECT isAttempted FROM quiz WHERE isAttempted=1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if(cursor.getCount()== 10){
                IsAllAttempted = true;
            }
            else
                IsAllAttempted = false;
        }
        return IsAllAttempted;
    }
}