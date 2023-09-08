package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
	
		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.print("Digite um ID para pesquisar um vendedor: ");
		Scanner in = new Scanner(System.in);
		Integer n = in.nextInt();
		
		Seller seller = sellerDao.findById(n);
		
		System.out.println("Segue abaixo os dados do vendedor desejado:\n\n");
		System.out.println("------------------------");
		System.out.println();
		System.out.println(seller);
	}

}
