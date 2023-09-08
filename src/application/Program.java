package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
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
		
		System.out.println("------------------------");
		System.out.println();
		
		System.out.println("Consultar por departamento:");
		n = in.nextInt();
		Department department = new Department(n, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		
		for(Seller obj : list) {
			System.out.println(obj);
		}
	}

}
