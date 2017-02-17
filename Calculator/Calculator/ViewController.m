//
//  ViewController.m
//  Calculator
//
//  Created by Andrew Grossnickle on 1/18/15.
//  Copyright (c) 2015 agrossnickle_gwalker. All rights reserved.
//

#import "ViewController.h"
#import "CalculatorBrain.h"
#import "GraphViewController.h"


@interface ViewController ()

@property (nonatomic) BOOL userIsInTheMiddle;
@property (nonatomic, strong) CalculatorBrain *brain;
@end

@implementation ViewController

@synthesize display;
@synthesize history;
@synthesize userIsInTheMiddle;
@synthesize brain = _brain;

- (CalculatorBrain *)brain
{
    if (!_brain) _brain = [[CalculatorBrain alloc] init];
    return _brain;
}

- (IBAction)digitPressed:(UIButton *)sender {
    
    NSString *digit = [sender currentTitle];
    
    BOOL decimalPresent = [display.text rangeOfString:@"."].location == NSNotFound ? NO : YES;
    
    if (self.userIsInTheMiddle) {
        if (([digit isEqualToString:@"."] && !decimalPresent) || !([digit isEqualToString:@"."])) {
            self.history.text = [self.history.text stringByAppendingString:digit];
            self.display.text = [self.display.text stringByAppendingString:digit];
        }
    }
    else if ([digit isEqualToString:@"0"] && [digit isEqualToString:@"."]) {
        self.history.text = [self.history.text stringByAppendingString:digit];
        self.display.text = [self.display.text stringByAppendingString:digit];
        userIsInTheMiddle = YES;
    } else {
        self.history.text = [self.history.text stringByAppendingString:@" "];
        self.history.text = [self.history.text stringByAppendingString:digit];
        self.display.text = digit;
        self.userIsInTheMiddle = YES;
    }
    
}

- (IBAction)variablePressed:(UIButton *)sender {
    
    NSString *variable = [sender currentTitle];
    
    [self.brain pushVariable:sender.currentTitle];
    
    self.history.text = [self.history.text stringByAppendingString:@" "];
    self.history.text = [self.history.text stringByAppendingString:variable];
    self.display.text = variable;
    
}


- (IBAction)enterPressed {
    [self.brain pushOperand:[self.display.text doubleValue]];
    self.userIsInTheMiddle = NO;
}

- (IBAction)operationPressed:(id)sender {
    if (self.userIsInTheMiddle) {
        [self enterPressed];
    }
    NSString *operation = [sender currentTitle];
    self.history.text = [self.history.text stringByAppendingString:@" "];
    if (self.history.text.length > 25)
        self.history.text = @"";
    self.history.text = [self.history.text stringByAppendingString:operation];
    double result = [self.brain performOperation:operation];
    self.display.text = @"=";
    self.display.text = [self.display.text stringByAppendingString:[NSString stringWithFormat:@"%g", result]];
}

- (IBAction)clearPressed {
    [self.brain popAllOperands];
    self.history.text = @"";
    self.display.text = @"0";
    self.userIsInTheMiddle = NO;
}


/*- (IBAction)graphPressed:(id)sender {
   	
    if ([self graphViewController]) {
        [[self graphViewController] setProgram:self.brain.program];
        [[self graphViewController] refreshView ];
    } else {
        [self performSegueWithIdentifier:@"DisplayGraphView" sender:self];
    }
    
}*/

 /*
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    [segue.destinationViewController setProgram:self.brain.program];
}
*/

@end
