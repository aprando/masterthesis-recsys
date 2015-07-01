package br.com.aprando.recommendersystem.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.GenericItemSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class MahoutHelloWorld {
	
	public static void main(String[] args) {
		int userId = 1;
		int totalItens = 3;
		System.out.println(" #### USER BASED RECOMMENDER RESULTS #### ");
		System.out.println( "   userId: " + userId);
		System.out.println( "   totalItens: " + totalItens);
		for(RecommendedItem item : userBasedRecommender(userId, totalItens)){
			System.out.println("ItemID : " + item.getItemID() + ", rating : " + item.getValue());
		}

		/*
		System.out.println(" #### ITEM BASED RECOMMENDER RESULTS #### ");
		System.out.println( "   userId: " + userId);
		System.out.println( "   totalItens: " + totalItens);
		for(RecommendedItem item : itemBasedRecommender(userId, totalItens)){
			System.out.println("ItemID : " + item.getItemID() + ", rating : " + item.getValue());
		}
		*/

	
	}

	public static List<RecommendedItem> userBasedRecommender(int userId, int totalItens){
		try {
			// ### USER BASED ##
			// create a DataModel of some kind. Here, we'll use a simple on based on data in a file. 
			// The file should be in CSV format, with lines of the form 
			// "userID,itemID,prefValue" (e.g. "39505,290002,3.5"):
			
			DataModel model = new FileDataModel(new File(MahoutHelloWorld.class.getClassLoader().getResource("mahout-hello-world.txt").getFile()));
			
			//We'll use the PearsonCorrelationSimilarity implementation of UserSimilarity 
			//as our user correlation algorithm, and add an optional preference inference algorithm:
			UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(model);
			
			//Now we create a UserNeighborhood algorithm. Here we use nearest-3:
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, userSimilarity, model);
			
			//Now we can create our Recommender, and add a caching decorator:
			Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, userSimilarity);
			Recommender cachingRecommender = new CachingRecommender(recommender);
			
			//Now we can get 10 recommendations for user ID "1234" — done!
			return cachingRecommender.recommend(userId, totalItens);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<RecommendedItem>();

	}
	
	public static List<RecommendedItem> itemBasedRecommender(int userId, int totalItens){
		try {
			// ### ITEM BASED ##
			// Item-based recommenders base recommendation not on user similarity, 
			// but on item similarity. In theory these are about the same approach to the problem, 
			// just from different angles. However the similarity of two items is relatively fixed, 
			// more so than the similarity of two users. So, item-based recommenders can use pre-computed 
			// similarity values in the computations, which make them much faster. For large data sets, 
			// item-based recommenders are more appropriate.
			DataModel model = new FileDataModel(new File("mahout-hello-world.txt"));
			
			//We'll also need an ItemSimilarity. We could use PearsonCorrelationSimilarity, which computes 
			// item similarity in realtime, but, this is generally too slow to be useful. Instead, in a real
			// application, you would feed a list of pre-computed correlations to a GenericItemSimilarity
			// Construct the list of pre-computed correlations
			Collection<GenericItemSimilarity.ItemItemSimilarity> correlations = new ArrayList<GenericItemSimilarity.ItemItemSimilarity>();
			correlations.add(new GenericItemSimilarity.ItemItemSimilarity(1, 2, 0.5));
			ItemSimilarity itemSimilarity = new GenericItemSimilarity(correlations);
			
			Recommender recommender = new GenericItemBasedRecommender(model, itemSimilarity);
			Recommender cachingRecommender = new CachingRecommender(recommender);
			
			return cachingRecommender.recommend(userId, totalItens);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<RecommendedItem>();

	}
	
	
}
