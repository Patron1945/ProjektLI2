package com.kucharz.patryk.li;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;

public class Solver
{
	private static Solver instance;

	protected Solver()
	{

	}

	public static Solver getInstance()
	{
		if (instance == null)
			instance = new Solver();

		return instance;
	}

	public Boolean solveProblem(int[][] clauses, int[] assumps, int queens, int variables)
	{
		ISolver solver = SolverFactory.newLight();
		solver.newVar(variables);
		solver.setExpectedNumberOfClauses(clauses.length);

//		for(int i = 0; i < clauses.length; i++)
//		{
//			System.out.print("Clauses: ");
//			for(int j = 0; j < clauses[i].length; j++)
//			{
//				System.out.print(clauses[i][j] + " ");
//			} 
//			System.out.println();
//		}
		
		try
		{

			for (int i = 0; i < clauses.length; i++)
			{
				solver.addClause(new VecInt(clauses[i]));
			}

			IProblem problem = solver;
			if (problem.isSatisfiable(new VecInt(assumps)))
			{

				return true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return false;
	}
}
