//
//  CalculatorBrain.m
//  Calculator
//
//  Created by Andrew Grossnickle on 1/18/15.
//  Copyright (c) 2015 agrossnickle_gwalker. All rights reserved.
//

#import "CalculatorBrain.h"

@interface CalculatorBrain()
@property (nonatomic, strong) NSMutableArray *operandStack;
@end

@implementation CalculatorBrain

@synthesize operandStack = _operandStack;

- (NSMutableArray *)operandStack
{
    if (!_operandStack) {
        _operandStack = [[NSMutableArray alloc] init];
    }
    return _operandStack;
}

- (void)pushOperand:(double)operand
{
    NSNumber *operandObject = [NSNumber numberWithDouble:operand];
    [self.operandStack addObject:operandObject];
}

- (void)pushVariable:(NSString*)variable
{
    [self.operandStack addObject:variable];
}

- (double)popOperand
{
    NSNumber *operandObject = [self.operandStack lastObject];
    if (operandObject) [self.operandStack removeLastObject];
    return [operandObject doubleValue];
}

- (void)popAllOperands
{
    for (int i=0;i < [_operandStack count];i++) {
        [self popOperand];
    }
}

- (double) performOperation:(NSString *)operation
{
    double result = 0;
    
    if ([operation isEqualToString:@"+"]) {
        result = [self popOperand] + [self popOperand];
    } else if ([operation isEqualToString:@"*"]) {
        result = [self popOperand] * [self popOperand];
    } else if ([operation isEqualToString:@"-"]) {
        double subtrahend = [self popOperand];
        result = [self popOperand] - subtrahend;
    } else if ([operation isEqualToString:@"/"]) {
        double divisor = [self popOperand];
        if (divisor) result = [self popOperand] / divisor;
    } else if ([operation isEqualToString:@"sin"]) {
        result = sin([self popOperand]);
    } else if ([operation isEqualToString:@"cos"]) {
        result = cos([self popOperand]);
    } else if ([operation isEqualToString:@"π"]) {
        result = M_PI;
    } else if ([operation isEqualToString:@"sqrt"]) {
        result = sqrt([self popOperand]);
    }
    
    [self pushOperand:result];
    
    return result;
}

/*
+ (id)runProgram:(id)program
usingVariableValues:(NSDictionary *)variableValues {
    
    
    NSMutableArray *stack= [program mutableCopy];
    
    // For each item in the program
    for (int i=0; i < [stack count]; i++) {
        id obj = [stack objectAtIndex:i];
        
        // See whether we think the item is a variable
        if ([obj isKindOfClass:[NSString class]] && ![self isOperation:obj]) {
            id value = [variableValues objectForKey:obj];
            // If value is not an instance of NSNumber, set it to zero
            if (![value isKindOfClass:[NSNumber class]]) {
                value = [NSNumber numberWithInt:0];
            }
            // Replace program variable with value.
            [stack replaceObjectAtIndex:i withObject:value];
        }		
    }	
    // Starting popping off the stack
    return [self popAllOperand:stack];
}
*/
 
@end
