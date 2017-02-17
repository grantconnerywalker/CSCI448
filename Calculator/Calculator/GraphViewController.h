//
//  GraphViewController.h
//  Calculator
//
//  Created by Andrew Grossnickle on 2/5/15.
//  Copyright (c) 2015 agrossnickle_gwalker. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "GraphView.h"

@interface GraphViewController : UIViewController <UISplitViewControllerDelegate>

@property (nonatomic, strong) id program;
@property (nonatomic, weak) IBOutlet GraphView *graphView;


- (void)refreshView;

@end
