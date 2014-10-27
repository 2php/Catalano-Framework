// Catalano Imaging Library
// The Catalano Framework
//
// Copyright � Diego Catalano, 2014
// diego.catalano at live.com
//
//    This library is free software; you can redistribute it and/or
//    modify it under the terms of the GNU Lesser General Public
//    License as published by the Free Software Foundation; either
//    version 2.1 of the License, or (at your option) any later version.
//
//    This library is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
//    Lesser General Public License for more details.
//
//    You should have received a copy of the GNU Lesser General Public
//    License along with this library; if not, write to the Free Software
//    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
//

package Catalano.Imaging.Tools;

import Catalano.Core.ArraysUtil;
import Catalano.Math.Decompositions.SingularValueDecomposition;
import Catalano.Math.Matrix;

/**
 * Kernel operations.
 * @author Diego Catalano
 */
public class Kernel {
    
    /**
     * Check if the kernel is normalized.
     * If the sum of elements its approximated equals 1.
     * 
     * @param kernel Kernel.
     * @return True if is normalized, otherwise false.
     */
    public static boolean isNormalized(double[][] kernel){
        
        double sum = Matrix.Sum(kernel);
        long n = Math.round(sum);
        if (n == 1) return true;
        return false;
        
    }
    
    /**
     * Check if the kernel is separable (Separable convolution).
     * @param kernel Kernel.
     * @return True if the kernel can be decomposed, otherwise false.
     */
    public static boolean isSeparable(int[][] kernel){
        
        double[][] m = ArraysUtil.toDouble(kernel);
        SingularValueDecomposition svd = new SingularValueDecomposition(m);
        return svd.rank() == 1;
        
    }
    
    /**
     * Convert kernel to normalized double values.
     * @param kernel Kernel.
     * @return Kernel.
     */
    public static double[][] toDouble(int[][] kernel){
        
        double sum = 0;
        for (int i = 0; i < kernel.length; i++) {
            for (int j = 0; j < kernel[0].length; j++) {
                sum += Math.abs(kernel[i][j]);
            }
        }
        
        double[][] k = new double[kernel.length][kernel[0].length];
        for (int i = 0; i < kernel.length; i++) {
            for (int j = 0; j < kernel[0].length; j++) {
                k[i][j] = kernel[i][j] < 0 ? -(kernel[i][j]/sum) : kernel[i][j] / sum;
            }
        }
        
        return k;
        
    }
    
    /**
     * Convert kernel to normalized integer values.
     * @param kernel Kernel.
     * @return Kernel.
     */
    public static int[][] toInt(double[][] kernel){
        
        double min = Matrix.Min(kernel);
        
        if (min == 0)
            throw new IllegalArgumentException("The kernel can't be normalized.");
        
        int[][] k = new int[kernel.length][kernel[0].length];
        for (int i = 0; i < kernel.length; i++) {
            for (int j = 0; j < kernel[0].length; j++) {
                k[i][j] = (int)(kernel[i][j] / min);
            }
        }
        
        return k;
        
    }
}