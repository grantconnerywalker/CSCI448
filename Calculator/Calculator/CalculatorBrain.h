//
//  CalculatorBrain.h
//  Calculator
//
//  Created by Andrew Grossnickle on 1/18/15.
//  Copyright (c) 2015 agrossnickle_gwalker. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <Math.h>

@interface CalculatorBrain : NSObject

- (void)pushVariable:(NSString*)variable;
- (void)pushOperand:(double)operand;
- (double) performOperation:(NSString *)operation;
- (void)popAllOperands;
- (double)popOperand;
//+ (id)popOperandOffProgramStack:(NSMutableArray *) stack;
//+ (id)runProgram:(id)program
//usingVariableValues:(NSDictionary *)variableValues;

@end
