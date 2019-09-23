package GoBear.initiations;

public class ConstantLib {
	static public class Travel
	{
		static public class Filter
		{
			static public class PromotionOpt
			{
				public static final String ShowAll = "Show All";
				public static final String PromotionOnly = "Promos Only";
			}	
			static public class InsurerOpt
			{
				public static final String FPGInsurance = "FPG Insurance";
				public static final String Malayan = "Malayan Insurance";
				public static final String PacificCross = "Pacific Cross";
				public static final String PioneerInsurance = "Pioneer Insurance";
				public static final String PrudentialGuarantee = "Prudential Guarantee";
				public static final String StandardInsurance = "Standard Insurance";
				public static final String STARR = "STARR";
			}
			static public class PersonalAccident
			{
				public static final int minValue = 0;
				public static final int maxValue = 3000000;
			}
			static public class MedicalExpenses
			{
				public static final int minValue = 0;
				public static final int maxValue = 10000000;
			}
			static public class TripCancellation
			{
				public static final int minValue = 0;
				public static final int maxValue = 100000;
			}
			static public class TripTermination
			{
				public static final int minValue = 0;
				public static final int maxValue = 150000;
			}
			static public class LossBaggage
			{
				public static final int minValue = 0;
				public static final int maxValue = 100000;
			}
			
		}
		
		public static class Sort
		{
			static public class sortOpt
			{
				public static final String Promotion = "Promotion";
				public static final String PriceLowToHigh = "Price: Low to high";
				public static final String PriceHighToLow = "Price: High to low";
				public static final String CoverageScoreHighToLow = "Coverage Score: High to low";
				public static final String InsurerAToZ = "Insurer: A to Z";
				public static final String InsurerZToA = "Insurer: Z to A";
				public static final String ReviewScoreHighToLow = "Review Score: High to low";
			}
		}
 	
		public static class Details
		{
			static public class policyTypeOpt
			{
				public static final String SingleTrip = "single trip";
				public static final String AnnualTrip = "annual trip";
			}
			
			static public class whoGoingOpt
			{
				public static final String JustMe = "just me";
				public static final String MyFamily = "my family";
				public static final String TwoPersons = "2 persons";
				public static final String ThreePersons = "3 me";
				public static final String FourPersons = "4 me";
				public static final String FivePersons = "5 me";
			}
			
			static public class detinationsOpt
			{
				public static final String HongKong = "Hong Kong";
				public static final String Philippines = "Philippines";
				public static final String ThaiLand = "ThaiLand";
				public static final String Japan = "Japan";
				public static final String Singapore = "Sinpapore";
				public static final String SouthKorea = "South Korea";
				// ...to be implemented more!	
			}
		}
		
		
	}
}
