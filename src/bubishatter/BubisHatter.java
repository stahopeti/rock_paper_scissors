/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubishatter;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 *
 * @author Lajos
 */
public class BubisHatter extends Application {
    
    private boolean radiob = false;
    ImageView eredmenyBal;
    ImageView eredmenyJobb;
    
    
    @Override
    public void start(Stage stage) {
        AnchorPane root = new AnchorPane();
        Group cir = new Group();
        Random rand = new Random();
        
        //A főmenü buborékok kijelzése
        for(int i=0;i<50;i++){
        
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            
            Circle c = new Circle(0.1, Color.rgb(r, g, b, 0.5));
            
            c.setCenterX(rand.nextInt(800));
            c.setCenterY(rand.nextInt(500));
            Timeline t = new Timeline();
            t.setCycleCount(Timeline.INDEFINITE);
            Duration d = Duration.seconds(rand.nextInt(30)+15);
            
            KeyValue kv = new KeyValue(c.radiusProperty(), rand.nextInt(100));
            
            KeyFrame kf = new KeyFrame(d,kv);
            
            t.getKeyFrames().add(kf);
            
            cir.getChildren().add(c);
            
            t.play();
            
            
        }
        //A főmenü buborékok kijelzése
        
        //Képek amiket használok
        //játékosok        
        Image player1 = new Image(BubisHatter.class.getResourceAsStream("player.png"));
        ImageView viewp1 = new ImageView();
        viewp1.setImage(player1);
        
        Image player2 = new Image(BubisHatter.class.getResourceAsStream("playerketto.png"));
        ImageView viewp2 = new ImageView();
        viewp2.setImage(player2);
        //játékosok
        
        
        
        Image ko_bal = new Image(BubisHatter.class.getResourceAsStream("ko_alap_bal.png"));
        ImageView viewBal = new ImageView();
        viewBal.setImage(ko_bal);
           
        Image ko_jobb = new Image(BubisHatter.class.getResourceAsStream("ko_alap_jobb.png"));
        ImageView viewJobb = new ImageView();
        viewJobb.setImage(ko_jobb);
        //Képek amiket használok
        
        
        
        //Gombok
        
        final Button btn = new Button("Kattintson a játékhoz!");
        final Button jtkStart = new Button("Játék indítása!");
        final Button fire = new Button("Mehet!");
        final Button vissza = new Button("Vissza a kezdőlapra!");
        
        //Gombok
        
        //működéshez szükséges misc
        HBox hboxJatek = new HBox();
            
        //
        root.getChildren().add(cir);
        root.getChildren().add(btn);
        
        PathTransition ptsBal = new PathTransition();
        PathTransition ptsJobb = new PathTransition();
        
        
       //menüből a játékhoz
        btn.setOnAction(new EventHandler<ActionEvent>(){
        
        @Override
        public void handle(ActionEvent e){
        
            hboxJatek.getChildren().add(jtkStart);
            hboxJatek.getChildren().add(vissza);
            root.getChildren().add(hboxJatek);
            
            viewp1.setX(0);
            viewp1.setY(100);
            
            
            viewp2.setX(600);
            viewp2.setY(100);
            
            root.getChildren().add(viewp1);
            root.getChildren().add(viewp2);
            
            
        
        }
        
        });
       //menüből a játékhoz 
        
        //játék indítása action
        jtkStart.setOnAction(new EventHandler<ActionEvent>(){
        
            
        @Override
        public void handle(ActionEvent e){

            
            root.getChildren().remove(hboxJatek);
            root.getChildren().remove(btn);
            root.getChildren().add(fire);
            root.getChildren().add(viewBal);
            root.getChildren().add(viewJobb);

            
            Path pathBal = new Path();
            pathBal.getElements().add(new MoveTo(300,250));
            pathBal.getElements().add(new LineTo(300,350));

            ptsBal.setDuration(Duration.millis(300));
            ptsBal.setPath(pathBal);
            ptsBal.setNode(viewBal);
            ptsBal.setCycleCount(Timeline.INDEFINITE);
            ptsBal.setAutoReverse(true);
            ptsBal.play();
            
            Path pathJobb = new Path();
            pathJobb.getElements().add(new MoveTo(500,250));
            pathJobb.getElements().add(new LineTo(500,350));

            ptsJobb.setDuration(Duration.millis(300));
            ptsJobb.setPath(pathJobb);
            ptsJobb.setNode(viewJobb);
            ptsJobb.setCycleCount(Timeline.INDEFINITE);
            ptsJobb.setAutoReverse(true);
            ptsJobb.play();
            
            }
        });
        //játék indítása action
        
        //játék elindítva
        fire.setOnAction(new EventHandler<ActionEvent>(){
        
        @Override
        public void handle(ActionEvent e){
        
            ptsBal.stop();
            ptsJobb.stop();
            root.getChildren().remove(viewBal);
            root.getChildren().remove(viewJobb);
            root.getChildren().remove(fire);
        
            int bal = rand.nextInt(3);
            int jobb = rand.nextInt(3);
        
            
            String balkep = new String();
            String jobbkep = new String();
            
            if(bal==0){balkep = "ko_alap_bal.png";}
            if(bal==1){balkep = "ollo_bal.png";}
            if(bal==2){balkep = "papir_bal.png";}
            
            if(jobb==0){jobbkep = "ko_alap_jobb.png";}
            if(jobb==1){jobbkep = "ollo_jobb.png";}
            if(jobb==2){jobbkep = "papir_jobb.png";}
            
            Image bal_eredmeny = new Image(BubisHatter.class.getResourceAsStream(balkep));
            eredmenyBal = new ImageView();
            eredmenyBal.setImage(bal_eredmeny);

            Image jobb_eredmeny = new Image(BubisHatter.class.getResourceAsStream(jobbkep));
            eredmenyJobb = new ImageView();
            eredmenyJobb.setImage(jobb_eredmeny);
            
            eredmenyBal.setX(200);
            eredmenyBal.setY(250);
            
            eredmenyJobb.setX(400);
            eredmenyJobb.setY(250);
            
            root.getChildren().add(eredmenyBal);
            root.getChildren().add(eredmenyJobb);
            
            root.getChildren().add(vissza);
            
        }
        
        
        });
        //játék elindítva
        
        //vissza a kezdőképernyőre
        vissza.setOnAction(new EventHandler<ActionEvent>(){
        
        @Override
        public void handle(ActionEvent e){
        
            root.getChildren().remove(fire);
            root.getChildren().remove(vissza);
            root.getChildren().remove(viewBal);
            root.getChildren().remove(viewJobb);
            root.getChildren().remove(viewp1);
            root.getChildren().remove(viewp2);
            root.getChildren().remove(eredmenyBal);
            root.getChildren().remove(eredmenyJobb);
            root.getChildren().add(btn);
        
        }
            
        
        });
        //vissza a kezdőképernyőre
        
        
        Scene scene = new Scene(root, 800,600);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        root.setId("pane");
        stage.show();
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
